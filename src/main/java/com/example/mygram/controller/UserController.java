package com.example.mygram.controller;

import com.example.mygram.model.dto.request.UserRequest;
import com.example.mygram.model.dto.response.ResponseData;
import com.example.mygram.model.entity.User;
import com.example.mygram.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
public class UserController {
    @Autowired
    UserService userService;

    private ResponseData<Object> responseData;

    @PostMapping(path = "/signup")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequest request) throws Exception {
        User user = userService.register(request);

        responseData = new ResponseData<Object>(200, "Success", user);
        return ResponseEntity.status(responseData.getCode()).body(responseData);
    }
}
