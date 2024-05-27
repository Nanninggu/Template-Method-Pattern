package com.example.Template.Method.Pattern.service;

import com.example.Template.Method.Pattern.dto.User;
import com.example.Template.Method.Pattern.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getUser(String id) {
        return userMapper.selectOne(id);
    }

}
