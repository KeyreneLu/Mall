package com.keyrene.service;

import com.keyrene.po.User;

/**
 * Created by DELL on 2017/7/20.
 */
public interface UserService {

    boolean insertByRegister(User user);

    boolean updateUserByCode(String code);

    Boolean selectUserByName(String username);
}
