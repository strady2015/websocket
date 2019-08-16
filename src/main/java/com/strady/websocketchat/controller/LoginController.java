package com.strady.websocketchat.controller;

import com.alibaba.fastjson.JSONObject;
import com.strady.websocketchat.core.WebResult;
import com.strady.websocketchat.domain.User;
import com.strady.websocketchat.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: strady
 * @Date: 2019/8/7
 * @Time: 9:28
 * @Description: 用户登录接口
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/")
    public String login(@RequestParam String userName,
                        @RequestParam String pwd) throws Exception {
        User user = userService.findUserByUserName(userName);
        if (user == null) {
            return WebResult.CODE(123).msg("用户名错误").toJSON();
        }
        String password = DigestUtils.md5DigestAsHex(Base64.encodeBase64((pwd + user.getSalt()).getBytes("utf-8")));
        if (!user.getPassword().equals(password)) {
            return WebResult.create().code(321).msg("密码错误").toJSON();
        }
        JSONObject result = new JSONObject();
        result.put("id", user.getId());
        result.put("userName", user.getUserName());
        result.put("nickName", user.getNickName());
        result.put("photoUrl", user.getPhotoUrl());
        result.put("gender", user.getGender());
        return result.toString();
    }
}