package com.keyrene.service.Impl;

import com.keyrene.mapper.UserMapper;
import com.keyrene.po.User;
import com.keyrene.po.UserExample;
import com.keyrene.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DELL on 2017/7/20.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean insertByRegister(User user) {
        int id = userMapper.insert(user);

        return id>0?true:false;
    }

    @Override
    public boolean updateUserByCode(String code) {
//        UserExample userExample = new UserExample();
//        UserExample.Criteria criteria = userExample.createCriteria();
//        criteria.andCodeEqualTo(code);

//        List<User> users = userMapper.selectByExample(userExample);

        int id = userMapper.updateByCode(code);


        return id>0?true:false;
    }

    @Override
    public Boolean selectUserByName(String username) {
        boolean isExit = false;
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        List<User> users = userMapper.selectByExample(example);

        if (users.size() == 0||users == null){
            isExit = false;
        }else if (users.size() == 1){
            isExit = true;
        }

        return isExit;
    }
}
