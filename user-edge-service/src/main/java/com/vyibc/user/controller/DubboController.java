package com.vyibc.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vyibc.course.ICourseService;
import com.vyibc.course.dto.CourseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Michael on 2017/10/30.
 */
@RestController
@RequestMapping("/user2")
public class DubboController {

    @Reference
    private ICourseService iCourseService;

    @RequestMapping(value="/getCourse", method = RequestMethod.GET)
    public List<CourseDTO>  getCourse() {
        System.out.println("哈哈哈");
        List<CourseDTO> courseDTOS = iCourseService.courseList();
        System.out.println(courseDTOS);
        return courseDTOS;
    }
}
