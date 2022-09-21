package edu.zut.vlog.controller;

import edu.zut.vlog.mapper.UserImgMapper;
import edu.zut.vlog.mapper.UsersMapper;
import edu.zut.vlog.pojo.ImgJson;
import edu.zut.vlog.pojo.Users;
import edu.zut.vlog.service.AlbumService;
import edu.zut.vlog.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/11
 * Time: 23:36
 **/
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserImgMapper userImgMapper;

    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping("/image")
    @ResponseBody
    public ImgJson image(MultipartFile file) {
        //调用工具类完成文件上传
        String imagePath = UploadUtil.upload(file);
        System.out.println("imagePath------" + imagePath);
        ImgJson dataJson = new ImgJson();
        if (imagePath != null) {
            //创建一个HashMap用来存放图片路径
            HashMap hashMap = new HashMap();
            hashMap.put("src", imagePath);
            dataJson.setCode(0);
            dataJson.setMsg("上传成功");
            dataJson.setData(hashMap);
        } else {
            dataJson.setCode(0);
            dataJson.setMsg("上传失败");
        }
        return dataJson;
    }


    @RequestMapping("/addCoverImage")
    @ResponseBody
    public String addCoverImage(HttpServletRequest request,String title, String imageDescribe, String imagePath) {
        //获得图片地址和图片描述
        /*
            这里我们只做打印的操作，实际上，我们应该在这里调用
            方法，把图片地址和图片的描述加入到数据库中，但是这些
            相信大家已经回了，所以，就不再写了。
         */
        Users nowUser = (Users) request.getSession().getAttribute("now_user");


        System.out.println("图片描述：" + imageDescribe);
        System.out.println("图片地址：" + imagePath);
        int row = albumService.addAlbum(title,imageDescribe,nowUser.getId(),imagePath);
        if (row > 0) {
            return "1";
        }

        return "0";
    }

    @RequestMapping("/addUserImage")
    @ResponseBody
    public String addUserImage(HttpServletRequest request,String imagePath){

        System.out.println("图片地址：" + imagePath);
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        System.out.println(">>>>>>nowAddUserAlbumId = " + albumId);
        int row = userImgMapper.addUserImg(albumId,imagePath);
        if (row > 0) {
            return "1";
        }
        return "0";
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(HttpServletRequest request,String imagePath,String petName,String password,String userIntroduction) {

        Users nowUser = (Users) request.getSession().getAttribute("now_user");

        System.out.println("nowUser.getId() = " + nowUser.getId());
        System.out.println("imagePath = " + imagePath);
        System.out.println("petName = " + petName);
        System.out.println("password = " + password);
        System.out.println("userIntroduction = " + userIntroduction);

        if (imagePath==null||imagePath.trim().length() == 0) {

            imagePath = nowUser.getHeadPortrait();
            System.out.println("imagePathhead = " + imagePath);
        }

        int row = usersMapper.updateUserByUserId(nowUser.getId(), imagePath,petName,password,userIntroduction);

        if (row > 0) {
            return "1";
        }return "0";
    }



    @RequestMapping("deleteImage")
    @ResponseBody
    public String deleteImage() {
        UploadUtil.delete("84e15dddb4284fc8a4877c93bcc9d81f-dl.jpg");
        return "1";
    }
}
