package edu.zut.vlog.controller;

import edu.zut.vlog.mapper.AlbumMapper;
import edu.zut.vlog.mapper.ArticleMapper;
import edu.zut.vlog.mapper.UsersMapper;
import edu.zut.vlog.pojo.Album;
import edu.zut.vlog.pojo.Article;
import edu.zut.vlog.pojo.Favorite;
import edu.zut.vlog.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/18
 * Time: 15:19
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/details")
    public String user(HttpServletRequest request, Model model) {

//        stringRedisTemplate.opsForHash().entries("");

        Users now_user = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", now_user);
//        System.out.println("now_user.getPetName() = " + now_user.getPetName());

//        int userId = Integer.parseInt(request.getParameter("userId"));
        if (now_user != null) {
            model.addAttribute("nowUserId", now_user.getId());
            Users user = usersMapper.getUserDetails(now_user.getId());
            model.addAttribute("user", user);

            List<Article> articles = articleMapper.queryAllByAuthorId(now_user.getId());
            model.addAttribute("articleList", articles);

            List<Album> albums = albumMapper.queryUserAlbumByUserId(now_user.getId());
            model.addAttribute("albumList", albums);
//        System.out.println(albums.get(0).getImgDescribe());

            List<Favorite> favoriteList = usersMapper.getUserFavorite(now_user.getId());
            model.addAttribute("favoriteList", favoriteList);
        }
        Users user = usersMapper.getUserDetails(Integer.parseInt(request.getParameter("userId")));
        model.addAttribute("user", user);

        List<Article> articles = articleMapper.queryAllByAuthorId(Integer.parseInt(request.getParameter("userId")));
        model.addAttribute("articleList", articles);

        List<Album> albums = albumMapper.queryUserAlbumByUserId(Integer.parseInt(request.getParameter("userId")));
        model.addAttribute("albumList", albums);
//        System.out.println(albums.get(0).getImgDescribe());

        List<Favorite> favoriteList = usersMapper.getUserFavorite(Integer.parseInt(request.getParameter("userId")));
        model.addAttribute("favoriteList", favoriteList);


        return "user";
    }


}
