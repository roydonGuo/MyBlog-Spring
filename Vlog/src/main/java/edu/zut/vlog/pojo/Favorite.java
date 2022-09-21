package edu.zut.vlog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/22
 * Time: 20:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    private Integer id;
    private Integer userId;//收藏者id
    private Integer articleId;//文章id
    private Timestamp createTime;

    private Article article;
    private Users user;
}
