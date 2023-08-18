package com.example.mygram.service.impl;

import com.example.mygram.exception.FoundException;
import com.example.mygram.model.dto.request.UserRequest;
import com.example.mygram.model.entity.User;
import com.example.mygram.repository.UserRepository;
import com.example.mygram.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User register(UserRequest request) throws Exception {
        Optional<User> isEmailExists = userRepository.findByEmail(request.getEmail());
        if (isEmailExists.isPresent()) {
            throw new FoundException("Email is exists");
        }

        Optional<User> isUsernameExists = userRepository.findByUsername(request.getUsername());
        if (isUsernameExists.isPresent()) {
            throw new FoundException("Username is exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));

        user = userRepository.save(user);
        return user;
    }
}
