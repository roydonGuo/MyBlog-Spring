package edu.zut.vlog.config;

import edu.zut.vlog.filter.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/9
 * Time: 20:11
 **/
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new UserLoginInterceptor());
        registration.addPathPatterns("/article/toeditor","/article/addFavorite","/article/moveFavorite"); //指定拦截路径，所有路径都被拦截/**
//        registration.addPathPatterns("/article/toeditor","/article/allarticle");
//        registration.excludePathPatterns(    //添加不拦截路径
//                "/login",                    //登录路径
//                "/",
//                "/article/allarticle",
//                "../**/*.html",                //html静态资源
//                "../**/*.js",                  //js静态资源
//                "../**/*.css"                  //css静态资源
//        );
    }
}
