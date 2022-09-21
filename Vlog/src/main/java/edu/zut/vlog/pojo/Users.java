package edu.zut.vlog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private Integer id;

    private String petName;
    private String password;

    private String headPortrait;//用户头像
    private String userIntroduction;//用户简介

//    private String school;

    private Timestamp inDate;//加入时间


    //    private List<Article> articleList;
}
