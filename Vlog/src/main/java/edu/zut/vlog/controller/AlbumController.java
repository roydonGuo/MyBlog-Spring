package edu.zut.vlog.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import edu.zut.vlog.mapper.AlbumMapper;
import edu.zut.vlog.mapper.UserImgMapper;
import edu.zut.vlog.pojo.Album;
import edu.zut.vlog.pojo.UserImg;
import edu.zut.vlog.pojo.Users;
import edu.zut.vlog.service.AlbumService;
import edu.zut.vlog.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/11
 * Time: 15:05
 **/
@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private UserImgMapper userImgMapper;

    @GetMapping("/allAlbum")
    public String allAlbum(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("now_user");

        model.addAttribute("now_user", user);

        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        System.out.println("pageNo = " + pageNo);
        PageInfo<Album> pageInfo = albumService.queryAlbumPageList(pageNo, 3);
        model.addAttribute("albumList", pageInfo.getList());
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNo", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());
//        List<Album> list = albumService.queryAllAlbum();
//
//        model.addAttribute("albumList",list);

        return "allalbum";

    }

    @GetMapping("/myAlbum")
    public String myAlbum(HttpServletRequest request, Model model) {
        Users user = (Users) request.getSession().getAttribute("now_user");

        model.addAttribute("now_user", user);

        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        System.out.println("pageNo = " + pageNo);
        PageInfo<Album> pageInfo = albumService.queryMyAlbums(user.getId(), pageNo, 300);
        model.addAttribute("albumList", pageInfo.getList());
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNo", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());
//        System.out.println(pageInfo.getList().get(0).getImgDescribe());
//        List<Album> list = albumService.queryAllAlbum();
//
//        model.addAttribute("albumList",list);

        return "myalbum";

    }

    @GetMapping("/deleteAlbum")
    public String deleteAlbum(HttpServletRequest request, Model model) {

        /**
         * 多表关联删除数据
         */
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        System.out.println("albumId=" + albumId);

        /**
         * 删除相册图片之前删除服务器文件
         */
        List<String> imgsUrl = albumMapper.queryImgsUrl(albumId);
//        List<String> imgUrlList = Arrays.asList(imgUrl.split("/"));
        for (String imgUrl : imgsUrl) {
            List<String> str = Arrays.asList(imgUrl.split("/"));
            UploadUtil.delete(str.get(4));
        }
//        System.out.println(imgUrlList.get(4));
//        UploadUtil.delete(imgUrlList.get(4));

        albumMapper.deleteImgs(albumId);

        /**
         * 删除相册封面之前删除服务器文件
         */
        String coverImgUrl = albumMapper.getAlbumCoverge(albumId);
        List<String> coverImgUrlList = Arrays.asList(coverImgUrl.split("/"));
        System.out.println(coverImgUrlList.get(4));
        UploadUtil.delete(coverImgUrlList.get(4));

        albumMapper.deleteAlbum(albumId);

        Users user = (Users) request.getSession().getAttribute("now_user");

        model.addAttribute("now_user", user);

        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        System.out.println("pageNo = " + pageNo);
        PageInfo<Album> pageInfo = albumService.queryMyAlbums(user.getId(), pageNo, 300);
        model.addAttribute("albumList", pageInfo.getList());
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNo", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());


        return "myalbum";
    }


    @GetMapping("/allImgs")
    public String allImages(HttpServletRequest request, Model model) {
        int albumId = Integer.parseInt(request.getParameter("id"));
        System.out.println("albumId = " + albumId);
        model.addAttribute("albumId", albumId);
        List<UserImg> list = userImgMapper.queryAllUserImgs(albumId);
        model.addAttribute("imgList", list);
        Album thisAlbum = albumMapper.queryTitleById(albumId);
        model.addAttribute("thisAlbum",thisAlbum);

        return "showimg";

    }


    @GetMapping("/imgs")
    public String imgs(HttpServletRequest request, Model model) {

        Users now_user = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", now_user);
        if (now_user!=null) {
            System.out.println("now_user.getId() = " + now_user.getId());
        }

        int albumId = Integer.parseInt(request.getParameter("id"));
        System.out.println("albumId = " + albumId);
        model.addAttribute("albumId", albumId);
        int albumUserId = albumMapper.queryUserIdByAlbumId(albumId);
        model.addAttribute("albumUserId", albumUserId);
        List<UserImg> list = userImgMapper.queryAllUserImgs(albumId);
        model.addAttribute("imgList", list);

        return "imgs";
    }

    @GetMapping("/deleteImg")
    public String deleteImg(HttpServletRequest request, Model model) {
        Users now_user = (Users) request.getSession().getAttribute("now_user");
        model.addAttribute("now_user", now_user);
        System.out.println("now_user.getId() = " + now_user.getId());
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        System.out.println("albumId = " + albumId);

        int albumUserId = albumMapper.queryUserIdByAlbumId(albumId);
        model.addAttribute("albumUserId", albumUserId);
        int imgId = Integer.parseInt(request.getParameter("id"));
        /**
         * 先删除服务器文件
         */
        String imgUrl = albumMapper.queryImgUrl(imgId);
        System.out.println("imgUrl = " + imgUrl);
        List<String> imgUrlList = Arrays.asList(imgUrl.split("/"));
        System.out.println(imgUrlList.get(4));
        UploadUtil.delete(imgUrlList.get(4));

        userImgMapper.deleteUserImgByImgId(imgId);
        List<UserImg> list = userImgMapper.queryAllUserImgs(albumId);
        model.addAttribute("imgList", list);

//        String str = "http://localhost:8080/upload/1ecbf04eca264f7f88c43cebe9d0d03f-thumbbig-427857.jpg";


        return "imgs";
    }


    //博客图片上传问题
    @RequestMapping("/file/upload")
    @ResponseBody
    public JSONObject fileUpload(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) throws IOException {
        //上传路径保存设置


        //获得SpringBoot当前项目的路径：System.getProperty("user.dir")
        String path = System.getProperty("user.dir") + "/upload/";
        System.out.println(path);
        //按照月份进行分类：
        Calendar instance = Calendar.getInstance();
        String month = (instance.get(Calendar.MONTH) + 1) + "mouthimg";
        path += month;

        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }

        //上传文件地址
        System.out.println("上传文件保存地址：" + realPath);

        //解决文件名字问题：我们使用uuid;
        String filename = "ks-" + UUID.randomUUID().toString().replaceAll("-", "");
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(new File(realPath + "/" + filename));

        //给editormd进行回调
        JSONObject res = new JSONObject();
        res.put("url", "/upload/" + month + "/" + filename);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;
    }

//    //添加相册
//    @PostMapping("/addAlbum")
//    @ResponseBody
//    public String addAlbum(Album album, HttpServletRequest request, Model model)
//    {
//        HttpSession session = request.getSession();
//        Users user = (Users) session.getAttribute("now_user");
//        //album的创建时间
//
//
//        System.out.println(album.getTitle());
//        System.out.println(album.getDescribe());
//        System.out.println(album.getCoverImgUrl());
//        System.out.println(request.getParameter("coverimgurl"));
//        //保存album
//        albumService.addAlbum(album.getTitle(),album.getDescribe(), user.getId(),request.getParameter("coverimgurl"));
//
//        return "yes";
//    }


}
