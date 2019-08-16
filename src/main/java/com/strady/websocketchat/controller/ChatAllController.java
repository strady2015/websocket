package com.strady.websocketchat.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @Author: strady
 * @Date: 2019/7/5
 * @Time: 13:52
 * @Description:
 */
@RestController
public class ChatAllController {

    /**
     * 登录界面
     *
     * @return
     */
    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /**
     * 聊天界面
     *
     * @param username
     * @param password
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/index")
    public ModelAndView index(String username, String password, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(username)) {
            username = "匿名用户";
        }
        ModelAndView view = new ModelAndView("/chat");
        view.addObject("username", username);
        view.addObject("webSocketUrl", "ws://" + InetAddress.getLocalHost().getHostAddress() + ":"
                + request.getServerPort() + request.getContextPath() + "/chat/" + username);
        return view;
    }
}
