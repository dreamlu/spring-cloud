package com.wbkjcom.wbkj.service;

import com.wbkjcom.user.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("user-service")
public interface UserService {

    @PostMapping("/user/login")
    User login(@RequestBody User user);

    @PostMapping("/user/insertUser") Boolean register(@RequestBody User user);
}
