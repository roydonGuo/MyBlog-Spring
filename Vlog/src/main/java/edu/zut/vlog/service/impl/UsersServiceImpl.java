package edu.zut.vlog.service.impl;


import edu.zut.vlog.mapper.UsersMapper;
import edu.zut.vlog.pojo.Users;
import edu.zut.vlog.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersMapper usersMapper;

    /*登录校验*/
    @Override
    public Users loginCheck(String name, String password) {

        if (usersMapper.loginCheck(name,password)==null){
            return null;
        }




        return usersMapper.loginCheck(name, password);

    }

    /*注册用户*/
    @Override
    public boolean registerUser(Users user) {
        return usersMapper.register(user);
    }

    @Override
    public boolean isExist(String name) {
        return usersMapper.isExist(name);
    }
}
