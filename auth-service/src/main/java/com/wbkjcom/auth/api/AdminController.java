package com.wbkjcom.auth.api;

import com.wbkjcom.auth.model.Admin;
import com.wbkjcom.auth.service.AdminService;
import com.wbkjcom.commons.lib.Lib;
import com.wbkjcom.commons.manager.TokenManager;
import com.wbkjcom.commons.manager.impl.RedisTokenManager;
import com.wbkjcom.commons.model.TokenModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * des: 登录等权限验证操作
 * author: dreamlu
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

//    private RedisTemplate<Long, String> redis = new RedisTemplate<Long, String>();

    private TokenManager tokenManager = new RedisTokenManager(new RedisTemplate<Long, String>());

    @Autowired
    private AdminService adminService;

    @Autowired
    DiscoveryClient discoveryClient;

    @PostMapping(value = "/login")
    public Object login(@RequestBody Admin admin) {
        admin = adminService.login(admin);

        if(admin != null) {
            TokenModel model = tokenManager.createToken(admin.getId());
            return Lib.GetMapData(Lib.CodeSuccess, Lib.MsgSuccess, model);
        }

        return Lib.MapNoAuth;
    }
}
