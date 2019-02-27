package com.wbkjcom.shop.service;

import com.wbkjcom.shop.model.User;


public interface UserService {
    User findByName(String name);

    User save(User user);
}
