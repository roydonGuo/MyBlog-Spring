//package edu.zut.vlog.filter;
//
//import edu.zut.vlog.utils.UserHolder;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by Intellij IDEA
// * Author: yi cheng
// * Date: 2022/5/26
// * Time: 16:26
// **/
////@Component
//public class LoginInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        if (UserHolder.getUser()==null) {
//            //没有就拦截
//            response.setStatus(401);
//            return false;
//        }
//
//        return true;
//    }
//
//}
