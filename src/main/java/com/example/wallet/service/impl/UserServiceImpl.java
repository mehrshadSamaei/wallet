package com.example.wallet.service.impl;

import com.example.wallet.base.BaseServiceImpl;
import com.example.wallet.entity.User;
import com.example.wallet.repository.UserRepository;
import com.example.wallet.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
