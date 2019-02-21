package com.kedacom.user.service.impl;

import com.kedacom.user.model.User;
import com.kedacom.user.repository.UserRepository;
import com.kedacom.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override public User save(User user) {
        return userRepository.save(user);
    }
}
