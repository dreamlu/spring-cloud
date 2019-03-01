package com.wbkjcom.auth.service;

import com.wbkjcom.auth.model.Admin;

/**
 * admin
 *
 * */

public interface AdminService {

    // 查看账号
    Admin findByAccount(String account);

    // 登录验证
    Admin login(Admin admin);

    // 注册
    Admin register(Admin admin);
}
