package com.vyibc.user.controller;

import com.imooc.thrift.message.MessageService;
import com.vyibc.course.ICourseService;
import com.vyibc.thrift.user.UserInfo;
import com.vyibc.thrift.user.UserService;
import com.vyibc.thrift.user.dto.UserDTO;
import com.vyibc.user.redis.RedisClient;
import com.vyibc.user.response.LoginResponse;
import com.vyibc.user.response.Response;
import com.vyibc.user.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.dubbo.config.annotation.Reference;
import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by Michael on 2017/10/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ServiceProvider serviceProvider;

    @Reference
    private ICourseService iCourseService;

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping(value="/regist", method = RequestMethod.GET)
    public ModelAndView regist() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("regist");
        return modelAndView;
    }

    @RequestMapping(value = "/login" , method = { RequestMethod.POST })
    public Response login( com.vyibc.user.controller.UserRequest userRequest) {
        System.out.println("用户开始登录");
        System.out.println(iCourseService.courseList());
        String password = userRequest.getPassword();
        String username = userRequest.getUsername();
        //1. 验证用户名密码
        UserInfo userInfo = null;
        try {
            UserService.Client userService = serviceProvider.getUserService();
            userInfo = userService.getUserByName(username);
        } catch (TException e) {
            e.printStackTrace();
            return Response.SERVICE_ERROR;
        }
        if(userInfo==null) {
            return Response.USERNAME_EMPTY;
        }
        String s = md5(password);
        //数据库密码
        System.out.println("数据库密码:"+s);
        if(!userInfo.getPassword().equalsIgnoreCase(md5(password))) {
            return Response.USERNAME_PASSWORD_INVALID;
        }

        //2. 生成token
        String token = genToken();

        //3. 缓存用户
        redisClient.set(token, toDTO(userInfo), 3600);

        return new LoginResponse(token);
    }

    @RequestMapping(value = "/sendVerifyCode", method = { RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    public Response sendVerifyCode(@RequestParam(value="mobile", required = false) String mobile,
                                   @RequestParam(value="email", required = false) String email) {

        String message = "Verify code is:";
        String code = randomCode("0123456789", 6);
        try {

            boolean result = false;
            if(StringUtils.isNotBlank(mobile)) {
                result = serviceProvider.getMessasgeService().sendMobileMessage(mobile, message+code);
                redisClient.set(mobile, code);
            } else if(StringUtils.isNotBlank(email)) {
                MessageService.Client messasgeService = serviceProvider.getMessasgeService();
                result = messasgeService.sendEmailMessage(email, message+code);

                redisClient.set(email, code);
            } else {
                return Response.MOBILE_OR_EMAIL_REQUIRED;
            }

            if(!result) {
                return Response.SEND_VERIFYCODE_FAILED;
            }
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }

        return Response.SUCCESS;

    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam(value="mobile", required = false) String mobile,
                             @RequestParam(value="email", required = false) String email,
                             @RequestParam("verifyCode") String verifyCode) {

        if(StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if(StringUtils.isNotBlank(mobile)) {
            String redisCode = redisClient.get(mobile);
            if(!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }else {
            String redisCode = redisClient.get(email);
            if(!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(md5(password));
        userInfo.setMobile(mobile);
        userInfo.setEmail(email);

        try {
            serviceProvider.getUserService().regiserUser(userInfo);
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }

        return Response.SUCCESS;
    }

    @RequestMapping(value="/authentication", method =  { RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    public UserDTO authentication(@RequestHeader("token") String token) {
        return redisClient.get(token);
    }

    private UserDTO toDTO(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);
        return userDTO;
    }

    private String genToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    private String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder(size);

        Random random = new Random();
        for(int i=0;i<size;i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }

    private String md5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(password.getBytes("utf-8"));
            return HexUtils.toHexString(md5Bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
