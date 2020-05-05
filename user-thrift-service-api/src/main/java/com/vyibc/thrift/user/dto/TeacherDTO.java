package com.vyibc.thrift.user.dto;

/**
 * Created by Michael on 2017/11/3.
 */
public class TeacherDTO extends UserDTO {
    /** 介绍*/
    private String intro;
    /** 评级*/
    private int stars;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
