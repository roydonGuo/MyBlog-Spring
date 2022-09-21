package edu.zut.vlog.service;

import com.github.pagehelper.PageInfo;
import edu.zut.vlog.pojo.Article;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/9
 * Time: 18:01
 **/
public interface ArticleService {

    PageInfo<Article> queryPageList(Integer pageNum, Integer pageSize);

    PageInfo<Article> queryPageMy(Integer authorId,Integer pageNum,Integer pageSize);

    PageInfo<Article> queryLike(String title,Integer pageNum,Integer pageSize);

    PageInfo<Article> queryLikeTag(String tagLike,Integer pageNum,Integer pageSize);
}
