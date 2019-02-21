package com.kedacom.user.service;

import com.kedacom.user.model.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {
    User findByName(String name);

    User save(User user);
}
