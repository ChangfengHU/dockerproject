package com.vyibc.course.service;


import com.vyibc.course.mapper.CourseMapper;
import com.vyibc.course.thrift.ServiceProvider;
import com.vyibc.course.ICourseService;
import com.vyibc.course.dto.CourseDTO;
import com.vyibc.thrift.user.UserInfo;
import com.vyibc.thrift.user.dto.TeacherDTO;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public List<CourseDTO> courseList() {
        List<CourseDTO> courseDTOS = courseMapper.listCourse();
        if(courseDTOS!=null) {
            for(CourseDTO courseDTO : courseDTOS) {
                Integer teacherId = courseMapper.getCourseTeacher(courseDTO.getId());
                if(teacherId!=null) {
                    try {
                        UserInfo userInfo = serviceProvider.getUserService().getTeacherById(teacherId);
                        courseDTO.setTeacher(trans2Teacher(userInfo));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return courseDTOS;
    }

    private TeacherDTO trans2Teacher(UserInfo userInfo) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(userInfo, teacherDTO);
        return teacherDTO;
    }
}
