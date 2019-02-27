package com.wbkjcom.wbkj.controller;

import com.wbkjcom.commons.lib.Lib;
import com.wbkjcom.commons.manager.TokenManager;
import com.wbkjcom.commons.model.TokenModel;
import com.wbkjcom.commons.util.GetInfoUtil;
import com.wbkjcom.shop.model.User;
import com.wbkjcom.wbkj.annotation.Authorization;
import com.wbkjcom.wbkj.annotation.CurrentUser;
import com.wbkjcom.wbkj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于ACCESS TOKEN的进行权限验证
 * 获取和删除 token 的请求地址，在 Restful 设计中其实就对应着登录和退出登录的资源映射
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;// = new RedisTokenManager();

    @PostMapping("/login")
    public Object login (User user) {
//        Assert.notNull (user.getName(), "username can not be empty");
//        Assert.notNull (user.getPassword(), "password can not be empty");


        User u = userService.login(user);
        if (u == null || !u.getPassword ().equals (user.getPassword())) { // 密码错误
            // 提示用户名或密码错误
            return Lib.MapCountErr;
        }
        // 生成一个 token，保存用户登录状态
        TokenModel model = tokenManager.createToken (u.getId ());
        return GetInfoUtil.success(model);
    }

    @RequestMapping (method = RequestMethod.DELETE)
    @Authorization
    public Object logout (@CurrentUser User user) {
        tokenManager.deleteToken (user.getId ());
        return GetInfoUtil.success();
    }

    /**
     * ping
     */
    @RequestMapping("/ping")
    public String test() {
        return "pong";
    }

}
