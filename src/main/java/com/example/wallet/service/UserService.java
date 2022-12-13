package com.example.wallet.service;

import com.example.wallet.base.BaseService;
import com.example.wallet.entity.User;
import org.springframework.stereotype.Service;

public interface UserService extends BaseService<User, Long> {
    User findByUsername(String username);
}
