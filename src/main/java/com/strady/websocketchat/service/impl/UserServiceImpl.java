package com.strady.websocketchat.service.impl;

import com.strady.websocketchat.domain.User;
import com.strady.websocketchat.repository.UserRepository;
import com.strady.websocketchat.service.UserService;
import com.strady.websocketchat.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: strady
 * @Date: 2019-08-15
 * @Time: 19:31:17
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查找所有用户
     *
     * @return
     */
    @Override
    public List<User> findUser() {
        return userRepository.findByIsDeletedIsFalse();
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    /**
     * 根据userName查找用户
     *
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findFirstByUserNameAndIsDeletedFalse(userName);
    }

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @Override
    public User finaUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}