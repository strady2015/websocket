package com.strady.websocketchat.service;

import com.strady.websocketchat.domain.User;

import java.util.List;

/**
 * @Author: strady
 * @Date: 2019-08-15
 * @Time: 19:29:51
 * @Description:
 */
public interface UserService {

    /**
     * 查找所有用户
     *
     * @return
     */
    List<User> findUser();

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * 根据userName查找用户
     *
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    User finaUserById(String id);
}
