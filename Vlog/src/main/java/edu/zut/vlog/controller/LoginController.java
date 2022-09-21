package edu.zut.vlog.controller;


import edu.zut.vlog.mapper.ArticleMapper;
import edu.zut.vlog.pojo.Favorite;
import edu.zut.vlog.pojo.Users;
import edu.zut.vlog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author yicheng
 */
@Controller
public class LoginController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(path = "login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model, Map<String, Object> map) {
        List<Favorite> fireArticles = articleMapper.fireArticles();
        model.addAttribute("fireArticles",fireArticles);
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println("now_name = " + name);
        System.out.println("now_password = " + password);
        Users now_user = usersService.loginCheck(name, password);
//        System.out.println("model_name="+now_user.getPetName());
//        System.out.println("model_password="+now_user.getPassword());
        if (now_user != null) {
//            Cookie cookie = new Cookie("nowLoginUser", name + "-" + password);
//            cookie.setMaxAge(24 * 60 * 60);
//            cookie.setPath("/");   //一定要加这个，不然前端document.cookie无法取出数据
//            response.addCookie(cookie);
//            map.put("cookie",cookie.getValue().split("-")[0]);
//            System.out.println(cookie.getValue().split("-")[0]);
            HttpSession session = request.getSession();
            session.setAttribute("now_user", now_user);
//            session.setMaxInactiveInterval(60*60*24);
//            System.out.println(session.getAttribute("now_user"));

            System.out.println("name>>>>>>"+now_user.getPetName());
            System.out.println("now_user_id>>>>>>"+now_user.getId());
            model.addAttribute("now_user", now_user);
//            model.addAttribute("now_user", BeanUtil.copyProperties(now_user, UserDTO.class));

//            String token = UUID.randomUUID().toString(true);
//            UserDTO userDTO = BeanUtil.copyProperties(now_user,UserDTO.class);
//            Map<String, Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
//                    CopyOptions.create()
//                            .setIgnoreNullValue(true)
//                            .setFieldValueEditor((fieldName,fieldValue)->fieldValue.toString()));
//            //转为HashMap存储
//            String tokenKey = LOGIN_USER_KEY+token;
//            stringRedisTemplate.opsForHash().putAll(tokenKey,userMap);
//            stringRedisTemplate.expire(tokenKey,LOGIN_USER_TTL, TimeUnit.MINUTES);//六十分钟

            return "index";
        }
        map.put("msg", "!please try again!");
        return "login";
    }

}
