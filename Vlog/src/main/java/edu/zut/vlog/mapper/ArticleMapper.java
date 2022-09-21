package edu.zut.vlog.mapper;

import edu.zut.vlog.pojo.Article;
import edu.zut.vlog.pojo.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/7
 * Time: 18:19
 **/
@Mapper
@Repository
public interface ArticleMapper {
    List<Article> queryAllArticles();
    //查询我的文章
    List<Article> queryAllByAuthorId(Integer authorId);
    int addArticle(int authorid,String title,String content);
    //根据文章id查询文章
    Article getArticleById(int id);

    int updateArticle(Article article);

    //根据文章id删除文章
    int deleteArticleById(Integer id);
    //模糊根据标题查询文章
    List<Article> queryArticleLikeTitle(String title);

    int countComment(Integer articleId);

    int addFavorite(Integer userId, Integer articleId, Timestamp createTime);

    Favorite weather(Integer userId, Integer articleId);
//    Favorite weatherUserFavorite(Integer userId,Integer articleId);

    List<Article> queryListOrderByDate();

    int addTag(Integer articleId,String tagContent);

    List<Article> queryByTagLike(String tagLike);

    /**
     * 删除文章前删除用户收藏
     */
    int delAllFavoriteByArticleId(Integer articleId);
    /**
     * 删除文章前删除文章标签
     */
    int delAllArticleTagByArticleId(Integer articleId);
    /**
     * 热门文章
     */
    List<Favorite> fireArticles();


}
