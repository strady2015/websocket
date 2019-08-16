package com.strady.websocketchat.service;

import com.strady.websocketchat.domain.User;

/**
 * @Author: strady
 * @Date: 2019/8/7
 * @Time: 9:26
 * @Description:
 */
public interface LoginService {

    User login(String username, String password);
}
