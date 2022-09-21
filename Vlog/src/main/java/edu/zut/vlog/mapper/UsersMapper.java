package edu.zut.vlog.mapper;

import edu.zut.vlog.pojo.Favorite;
import edu.zut.vlog.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author HP
 */
@Mapper
public interface UsersMapper {
    /**
     * 用户登录校验
     * @param name 用户名
     * @param password 密码
     * @return 当前登录用户
     */
    @Select("select * from users where pet_name=#{name} and password=#{password}")
    Users loginCheck(String name, String password);

    @Select("select * from user where id=#{id}")
    Users nowUser(Integer id);
//    @Select("select * from users where pet_name=#{pet_name} and password=#{password}")
//    Users testkogin(String pet_name,String password);
    /**
     * 注册
     * @param user 用户账号密码
     * @return boolean
     */
    boolean register(Users user);
    boolean isExist(String name);

    int updateUserByUserId(Integer userId,String imagePath,String petName,String password,String userIntroduction);

    Users getUserDetails(int userId);

    List<Favorite> getUserFavorite(Integer userId);


}
