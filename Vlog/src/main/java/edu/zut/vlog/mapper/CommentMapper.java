package edu.zut.vlog.mapper;

import edu.zut.vlog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/16
 * Time: 19:41
 **/
@Mapper
public interface CommentMapper {

    List<Comment> getCommentsByArticleId(int articleId);
/**
 * 增加评论
 */
    int addComment(Integer articleId, Integer userId, String content, Timestamp createTime);
    int delUserFavorite(Integer userId,Integer articleId);

    int delComment(Integer id);//主观删除评论
    int delCommentAllByArticleId(Integer articleId);//删除文章后，评论相应删除
}
