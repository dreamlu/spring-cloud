package com.kedacom.keda.service;

import com.kedacom.user.model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("user-service")
public interface UserService {

    @PostMapping("/user/login")
    User login(@RequestBody User user);

    @PostMapping("user/insertUser") Boolean register(@RequestBody User user);
}
