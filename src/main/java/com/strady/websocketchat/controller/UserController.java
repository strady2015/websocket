package com.strady.websocketchat.controller;

import com.strady.websocketchat.domain.User;
import com.strady.websocketchat.service.UserService;
import com.strady.websocketchat.util.UUIDUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: strady
 * @Date: 2019-08-15
 * @Time: 19:35:41
 * @Description: 用户接口
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping(value = "/")
    public String findUser() {
        return userService.findUser().toString();
    }

    /**
     * 添加新用户
     *
     * @param userName 用户名
     * @param nickName 昵称
     * @param photoUrl 头像
     * @param pwd      密码
     * @param email    电子邮箱
     * @param gender   性别
     * @param mobile   手机
     * @param remark   备注
     * @return
     */
    @PostMapping(value = "/add")
    public String addUser(@RequestParam String userName,
                          @RequestParam(required = false) String nickName,
                          @RequestParam(required = false) String photoUrl,
                          @RequestParam String pwd,
                          @RequestParam(required = false) String email,
                          @RequestParam(defaultValue = "false") Integer gender,
                          @RequestParam(required = false) String mobile,
                          @RequestParam(required = false) String remark) throws Exception {
        //获取6位随机字符串作为盐
        String salt = UUIDUtils.randomStr(6, false);
        //密码和盐拼接后Base64加密后再md5加密
        String password = DigestUtils.md5DigestAsHex(Base64.encodeBase64((pwd + salt).getBytes("utf-8")));
        //获取当前时间戳
        Long time = System.currentTimeMillis();
        User user = new User();
        user.setId(UUIDUtils.UUID());
        user.setUserName(userName);
        user.setNickName(nickName);
        user.setPhotoUrl(photoUrl);
        user.setPassword(password);
        user.setSalt(salt);
        user.setEmail(email);
        user.setGender(gender);
        user.setMobile(mobile);
        user.setCreateTime(time);
        user.setUpdateTime(time);
        user.setRemark(remark);
        return userService.addUser(user).toString();
    }

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/find")
    public String findUser(@RequestParam String id) {
        return userService.finaUserById(id).toString();
    }
}
