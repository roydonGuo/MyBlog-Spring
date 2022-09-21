package edu.zut.vlog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/16
 * Time: 19:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Integer commentId;
    private Integer articleId;
    private Integer userId;
    private String content;
    private Timestamp createTime;

    private Article article;
    private Users user;


}
