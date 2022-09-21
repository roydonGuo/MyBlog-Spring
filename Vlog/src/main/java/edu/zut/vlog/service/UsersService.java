package edu.zut.vlog.service;

import edu.zut.vlog.pojo.Users;

public interface UsersService {

    /**
     * 用户登录校验
     * @param name 用户名
     * @param password 密码
     * @return 当前登录用户
     */
    Users loginCheck(String name, String password);

    /**
     * register
     * @param user 用户账号密码
     * @return boolean
     */
    boolean registerUser(Users user);
    boolean isExist(String name);
}
