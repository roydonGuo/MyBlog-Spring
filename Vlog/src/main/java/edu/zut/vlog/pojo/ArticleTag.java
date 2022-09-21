package edu.zut.vlog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/24
 * Time: 18:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTag {

    private Integer tagId;
    private Integer articleId;
    private String tag;

    private Article article;

}
