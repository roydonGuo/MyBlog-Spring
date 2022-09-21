package edu.zut.vlog.controller;


import edu.zut.vlog.mapper.ArticleMapper;
import edu.zut.vlog.mapper.UsersMapper;
import edu.zut.vlog.pojo.Article;
import edu.zut.vlog.pojo.Users;
import edu.zut.vlog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private ArticleMapper articleMapper;
    @Resource
    private UsersService usersService;
    @Autowired
    private UsersMapper usersMapper;


    @GetMapping(path = "index")
    public String index() {

        return "index";
    }

    @GetMapping(path = "leimu")
    public String leimu() {
        return "leimu";
    }

    @GetMapping(path = "login")
    public String login() {
        return "login";
    }
    @GetMapping(path = "addAlbum")
    public String addAlbum(){
        return "addalbum";
    }
    @RequestMapping(path = "/updateUser")
    public String updateUser(HttpServletRequest request,Model model) {
        int userId = Integer.parseInt(request.getParameter("userId"));

        model.addAttribute("user",usersMapper.getUserDetails(userId));

        return "alteruser";
    }
    @GetMapping("/update")
    public String update(HttpServletRequest request,Model model){
        int id = Integer.parseInt(request.getParameter("id"));

        System.out.println("update_article_id = " + id);
        Article article = articleMapper.getArticleById(id);
        model.addAttribute("article", article);
        model.addAttribute("articleId",id);

        return "updateeditor";
    }

    /*用户注册*/
    @GetMapping(path = "/register")
    public ModelAndView addStudent(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return new ModelAndView("register", "userModel", model);
    }

    @PostMapping("/adduser")
    public ModelAndView adduser(HttpServletRequest request, Users user, Map<String, Object> map) {

        usersService.registerUser(user);
        return new ModelAndView("redirect:/page/login");
    }

}
