package com.example.mygram.service;

import com.example.mygram.model.dto.request.UserRequest;
import com.example.mygram.model.entity.User;

public interface UserService {
    User register(UserRequest request) throws Exception;
}
