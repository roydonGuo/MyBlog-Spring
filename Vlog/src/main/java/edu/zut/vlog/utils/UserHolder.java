package edu.zut.vlog.utils;

import edu.zut.vlog.dto.UserDTO;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/25
 * Time: 21:13
 **/
public class UserHolder {

    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static void saveUser(UserDTO user) {
        tl.set(user);
    }

    public static UserDTO getUser() {
        return tl.get();
    }

    public static void removeUser() {
        tl.remove();
    }

}
