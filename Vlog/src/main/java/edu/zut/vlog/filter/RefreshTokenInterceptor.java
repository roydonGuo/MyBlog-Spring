//package edu.zut.vlog.filter;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.util.StrUtil;
//import edu.zut.vlog.dto.UserDTO;
//import edu.zut.vlog.utils.UserHolder;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import static edu.zut.vlog.utils.RedisConstants.LOGIN_USER_KEY;
//import static edu.zut.vlog.utils.RedisConstants.LOGIN_USER_TTL;
//
///**
// * Created by Intellij IDEA
// * Author: yi cheng
// * Date: 2022/5/26
// * Time: 17:03
// **/
//public class RefreshTokenInterceptor implements HandlerInterceptor {
//
//    private StringRedisTemplate stringRedisTemplate;
//
//    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
//        this.stringRedisTemplate = stringRedisTemplate;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("authorization");
//        if (StrUtil.isBlank(token)) {
//            response.setStatus(401);//不存在，拦截
//            return false;
//        }
//        //基于token获取user对象
//        String tokenKey = LOGIN_USER_KEY + token;
//        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(tokenKey);//若不存在放回空map
//        if (userMap.isEmpty()) {
//            response.setStatus(401);//不存在，拦截
//            return false;
//        }
//        //转为userDTO
//        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
//        UserHolder.saveUser(userDTO);
//        //刷新token有效期
//        stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);
//        return true;
//
//    }
//
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        UserHolder.removeUser();
//    }
//}
