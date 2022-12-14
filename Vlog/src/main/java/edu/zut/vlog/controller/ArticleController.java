package edu.zut.vlog.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import edu.zut.vlog.mapper.ArticleMapper;
import edu.zut.vlog.mapper.CommentMapper;
import edu.zut.vlog.pojo.Article;
import edu.zut.vlog.pojo.Comment;
import edu.zut.vlog.pojo.Favorite;
import edu.zut.vlog.pojo.Users;
import edu.zut.vlog.service.ArticleService;
import edu.zut.vlog.utils.NowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/7
 * Time: 18:38
 **/
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentMapper commentMapper;

    @PostMapping("/addarticle")
    public String addArticle(Article article, HttpServletRequest request, Model model) throws InterruptedException {
        Users now_user = (Users) request.getSession().getAttribute("now_user");

        System.out.println("user_id:" + now_user.getId());
        int row = articleMapper.addArticle(now_user.getId(), article.getTitle(), article.getContent());
//        int addtag = articleMapper.addTag(article.getId(), )
        if (row > 0) {

//            Thread.sleep(2000);
//            int id = Integer.parseInt(request.getParameter("id"));
//            System.out.println("---------newaddarticleid = " + article.getId());
//            Article addedArticle = articleMapper.getArticleById(article.getId());
//
//            model.addAttribute("article", addedArticle);
            return "addsuccess";
        } else {
            return "error";
        }
        //????????????????????????
//        return "editor";
    }

    @GetMapping("/delArticle")
    public String deleteArticle(HttpServletRequest request, Model model) {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        /**
         * ???????????????????????????????????????????????????id???????????????????????????
         */
        int delCom = commentMapper.delCommentAllByArticleId(articleId);
        System.out.println("????????????????????????<<<" + delCom + ">>>?????????");

        int delFavCount = articleMapper.delAllFavoriteByArticleId(articleId);
        System.out.println("????????????????????????<<<" + delFavCount + ">>>????????????");

        int delTagCount = articleMapper.delAllArticleTagByArticleId(articleId);
        System.out.println("????????????????????????<<<" + delTagCount + ">>>??????");

        int delrow = articleMapper.deleteArticleById(articleId);
        System.out.println("?????????<<<" + delrow + ">>>?????????");
        Users nowUser = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", nowUser);

        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        System.out.println("?????????pageNo = " + pageNo);
        PageInfo<Article> pageInfo = articleService.queryPageMy(nowUser.getId(), pageNo, 6);
        model.addAttribute("articleList", pageInfo.getList());
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNo", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());
        System.out.println(pageInfo.getList().get(0).getDate());


        return "redirect:/user/details?userId=" + nowUser.getId();
    }

    @PostMapping("/updateArticle")
    public String updateArticle(Article article, HttpServletRequest request, Model model) throws ParseException {

        Users now_user = (Users) request.getSession().getAttribute("now_user");
        System.out.println("update_article.getId() = " + article.getId());

        Date date = new Date();//??????????????????.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);//??????????????????????????????Timestamp???????????????.
//        Date time=sdf.parse(nowTime);
        Timestamp dates = Timestamp.valueOf(nowTime);//???????????????
        article.setDate(dates);

        article.setId(Integer.valueOf(request.getParameter("id")));
        int row = articleMapper.updateArticle(article);

        String tagContent = request.getParameter("tagContent");
        System.out.println("tagContent = " + tagContent);

        int addtag = articleMapper.addTag(article.getId(), request.getParameter("tagContent"));

        if (row > 0) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            Article updatedArticle = articleMapper.getArticleById(article.getId());
//            model.addAttribute("article", updatedArticle);
            return "addsuccess";
        }
        return "error";

    }

    //????????????????????????
    @RequestMapping("/file/upload")
    @ResponseBody
    public JSONObject fileUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request) throws IOException {
        //????????????????????????


        //??????SpringBoot????????????????????????System.getProperty("user.dir")
        String path = System.getProperty("user.dir") + "/upload/";
        System.out.println(path);
        //???????????????????????????
        Calendar instance = Calendar.getInstance();
        String month = (instance.get(Calendar.MONTH) + 1) + "mouth";
        path = path + month;

        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }

        //??????????????????
        System.out.println("???????????????????????????" + realPath);

        //???????????????????????????????????????uuid;
        String filename = "ks-" + UUID.randomUUID().toString().replaceAll("-", "");
        //??????CommonsMultipartFile????????????????????????????????????????????????
        file.transferTo(new File(realPath + "/" + filename));

        //???editormd????????????
        JSONObject res = new JSONObject();
        res.put("url", "/upload/" + month + "/" + filename);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;
    }

    @GetMapping("/detail")
    public String show(HttpServletRequest request, Model model) {

        Users user = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", user);
        if (user == null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Article article = articleMapper.getArticleById(id);
            model.addAttribute("article", article);
            List<Comment> commentList = commentMapper.getCommentsByArticleId(id);
            model.addAttribute("commentList", commentList);
            return "article";
        }
        int id = Integer.parseInt(request.getParameter("id"));
        Article article = articleMapper.getArticleById(id);
        model.addAttribute("article", article);
        List<Comment> commentList = commentMapper.getCommentsByArticleId(id);
        model.addAttribute("commentList", commentList);

        Favorite weatherFavorite = articleMapper.weather(user.getId(), id);
        System.out.println("weatherFavorite = " + weatherFavorite);
        model.addAttribute("weatherFavorite", weatherFavorite);
        return "article";
    }

    /**
     * ??????????????????????????????
     *
     * @return likearticles??????
     */
//    @GetMapping("/querylike")
//    public String queryLike(HttpServletRequest request, Model model) {
//        String title = request.getParameter("title");
//
//        List<Article> articles = articleMapper.queryArticleLikeTitle(title);
//
//        model.addAttribute("articleList", articles);
//        return "likearticles";
//    }
    @GetMapping("/querylike")
    public String queryLike(HttpServletRequest request, Model model) {
        String title = request.getParameter("title");
        Users user = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", user);


        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        System.out.println("pageNo = " + pageNo);
        PageInfo<Article> pageInfo = articleService.queryLike(title, pageNo, 20);
        model.addAttribute("articleList", pageInfo.getList());
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNo", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());


        return "allarticles";
    }

    @RequestMapping("/queryLikeTag")
    public String queryLikeTag(HttpServletRequest request, Model model) {
        Users user = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", user);

        String tagLike = request.getParameter("tagLike");
        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        System.out.println("pageNo = " + pageNo);
        PageInfo<Article> pageInfo = articleService.queryLikeTag(tagLike, pageNo, 20);
        model.addAttribute("articleList", pageInfo.getList());
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNo", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());


        return "allarticles";


    }


    //    @GetMapping("/allarticle")
//    public String allArticle(Model model) {
//        List<Article> articles = articleMapper.queryAllArticles();
////        PageHelper.startPage(pageNum,pageSize);
////        PageInfo<Article> pageInfo = new PageInfo<>(articles);
//        model.addAttribute("articleList", articles);
//        return "likearticles";
//    }
    @GetMapping("/allarticle")
    public String allArticle(HttpServletRequest request, Model model) {

        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        System.out.println("pageNo = " + pageNo);
        PageInfo<Article> pageInfo = articleService.queryPageList(pageNo, 5);
        model.addAttribute("articleList", pageInfo.getList());
        model.addAttribute("pages", pageInfo.getPages());//?????????
        model.addAttribute("pageNo", pageInfo.getPageNum());//?????????
        model.addAttribute("pageSize", pageInfo.getPageSize());//??????????????????????????????

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("now_user");
        model.addAttribute("now_user", user);

        /**
         * ?????????
         *
         */
        List<Favorite> fireArticles = articleMapper.fireArticles();
        model.addAttribute("fireArticles",fireArticles);

        return "allarticles";
    }

    @RequestMapping("/dynamic")
    public String dynamic(HttpServletRequest request, Model model) {

        Users nowUser = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", nowUser);

        List<Article> articles = articleMapper.queryListOrderByDate();
        model.addAttribute("articleList", articles);


        return "dynamic";
    }

    @GetMapping("/myArticle")
    public String myArticle(HttpServletRequest request, Model model) {
        Users nowUser = (Users) request.getSession().getAttribute("now_user");
        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        System.out.println("pageNo = " + pageNo);
        PageInfo<Article> pageInfo = articleService.queryPageMy(nowUser.getId(), pageNo, 6);
        model.addAttribute("articleList", pageInfo.getList());
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNo", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());

        System.out.println("nowUser = " + nowUser);

        model.addAttribute("now_user", nowUser);
        return "myarticles";

    }


    @GetMapping("/toeditor")
    public String toEditor() {
        return "editor";
    }

    @PostMapping("/addComment")
    public String addComment(HttpServletRequest request, Model model) {
        Users nowUser = (Users) request.getSession().getAttribute("now_user");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int row = commentMapper.addComment(articleId, nowUser.getId(), request.getParameter("content"), NowTime.getTime());
        if (row > 0) {
            return "redirect:detail?id=" + articleId + "#pinglun";
        }
        return "error";
    }

    @RequestMapping("/delComment")
    public String delComment(HttpServletRequest request, Model model) {
        Users nowUser = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", nowUser);

        int delCom = commentMapper.delComment(Integer.valueOf(request.getParameter("id")));

        int id = Integer.parseInt(request.getParameter("articleId"));
        return "redirect:/article/detail?id=" + id;

    }


    @RequestMapping("/delFavorite")
    public String delFavorite(HttpServletRequest request, Model model) {
        Users nowUser = (Users) request.getSession().getAttribute("now_user");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int delRow = commentMapper.delUserFavorite(nowUser.getId(), articleId);
        if (delRow > 0) {
            return "redirect:/user/details?userId=" + nowUser.getId();

        }
        return "error";

    }

    @RequestMapping("/moveFavorite")
    public String moveFavority(HttpServletRequest request, Model model) {
        Users nowUser = (Users) request.getSession().getAttribute("now_user");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int delRow = commentMapper.delUserFavorite(nowUser.getId(), articleId);
        if (delRow > 0) {
            return "redirect:/article/detail?id=" + articleId;

        }
        return "error";

    }

    @RequestMapping("/addFavorite")
    public String addFavorite(HttpServletRequest request, Model model) {
        Users nowUser = (Users) request.getSession().getAttribute("now_user");
        int row = articleMapper.addFavorite(nowUser.getId(), Integer.valueOf(request.getParameter("articleId")), NowTime.getTime());
        request.setAttribute("row", row);
        if (row > 0) {
            return "redirect:/article/detail?id=" + Integer.valueOf(request.getParameter("articleId"));
        }
        return "error";

    }

    @RequestMapping("/SelectByTag")
    public String selectByTag(HttpServletRequest request, Model model) {
        String tagContent = request.getParameter("tagContent");
        System.out.println("tagContent = " + tagContent);

        Users nowUser = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", nowUser);

        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        System.out.println("pageNo = " + pageNo);
        PageInfo<Article> pageInfo = articleService.queryLikeTag(tagContent, pageNo, 6);
        model.addAttribute("articleList", pageInfo.getList());
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNo", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());

        return "allarticles";
    }


}
