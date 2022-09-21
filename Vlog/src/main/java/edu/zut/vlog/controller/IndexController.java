package edu.zut.vlog.controller;

import edu.zut.vlog.mapper.ArticleMapper;
import edu.zut.vlog.pojo.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/24
 * Time: 16:37
 **/
@Controller
public class IndexController {

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        List<Favorite> fireArticles = articleMapper.fireArticles();
        model.addAttribute("fireArticles",fireArticles);
        return "index";

    }


}
