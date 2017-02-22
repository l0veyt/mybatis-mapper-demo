package com.xinlee.demo.mapper;

import com.xinlee.demo.pojo.User;

import java.util.List;

/**
 * Created by xin.lee on 2017/2/22.
 * 用户持久层Mapper接口
 */
public interface UserMapper {

    List<User> selectUserList();

    int insertUser(User user);

    int deleteUserByName(String username);
}
