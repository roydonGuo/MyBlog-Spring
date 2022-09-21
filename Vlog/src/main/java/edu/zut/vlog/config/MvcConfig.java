//package edu.zut.vlog.config;
//
//import edu.zut.vlog.filter.LoginInterceptor;
//import edu.zut.vlog.filter.RefreshTokenInterceptor;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//
///**
// * Created by Intellij IDEA
// * Author: yi cheng
// * Date: 2022/5/25
// * Time: 18:35
// **/
////@Configuration
//public class MvcConfig implements WebMvcConfigurer {
//
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/article/toeditor")
//                .excludePathPatterns(
//                        "/article/**",
//                        "/album/**",
//                        "/upload/**",
//                        "/page/**"
//                ).order(1);//值越小优先级越高
//        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**").order(0);
//    }
//}
