package com.vyibc.user.service;

import com.vyibc.thrift.user.UserInfo;
import com.vyibc.thrift.user.UserService;
import com.vyibc.user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 25131 on 2019/11/2.
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    private UserMapper userMapper;
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    public UserInfo getTeacherById(int id) throws TException {
        return userMapper.getTeacherById(id);
    }

    public UserInfo getUserByName(String username) throws TException {
        System.out.println("收到服务 getUserByName:username:"+username);
        UserInfo userByName = userMapper.getUserByName(username);
        System.out.println(userByName);
        return userByName;
    }

    public void regiserUser(UserInfo userInfo) throws TException {
        userMapper.registerUser(userInfo);
    }
}
