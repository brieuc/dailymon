package com.brieuc.dailymon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.config.JwtService;
import com.brieuc.dailymon.user.User;



@RestController
@RequestMapping(value = "/auth")
public class AuthController {

      private final JwtService jwtService;

      @Autowired
      public AuthController(JwtService jwtService) {
            this.jwtService = jwtService;
      }

      @GetMapping(value = "/login")
      public void login(@RequestBody User user) {

      }

      @GetMapping(value = "/register")
      public void register(@RequestBody User user) {
           String token = jwtService.generateToken(null, user);
      }      

}

