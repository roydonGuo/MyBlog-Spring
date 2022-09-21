package edu.zut.vlog;

import edu.zut.vlog.mapper.ArticleMapper;
import edu.zut.vlog.mapper.CommentMapper;
import edu.zut.vlog.pojo.Favorite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VlogApplicationTests {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Test
    void contextLoads() {

        List<Favorite> list = articleMapper.fireArticles();
        list.forEach(System.out::println);

    }



}
