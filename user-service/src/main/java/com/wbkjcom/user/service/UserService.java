package com.wbkjcom.user.service;

import com.wbkjcom.user.model.User;


public interface UserService {
    User findByName(String name);

    User save(User user);
}
