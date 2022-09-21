package edu.zut.vlog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/7
 * Time: 18:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    private Integer id;
    private Integer authorId;
    private String title;
    private String content;
    private Timestamp date;//private LocalDate date;

    private Users user;

    private List<Comment> commentList;
    private List<ArticleTag> articleTagList;

}
