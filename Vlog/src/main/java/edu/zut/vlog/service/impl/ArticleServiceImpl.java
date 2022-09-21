package edu.zut.vlog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zut.vlog.mapper.ArticleMapper;
import edu.zut.vlog.pojo.Article;
import edu.zut.vlog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/9
 * Time: 18:02
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageInfo<Article> queryPageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.queryAllArticles();
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public PageInfo<Article> queryPageMy(Integer authorId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleMapper.queryAllByAuthorId(authorId);
        PageInfo<Article> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public PageInfo<Article> queryLike(String title, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.queryArticleLikeTitle(title);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }
    @Override
    public PageInfo<Article> queryLikeTag(String tagLike, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.queryByTagLike(tagLike);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }
}
