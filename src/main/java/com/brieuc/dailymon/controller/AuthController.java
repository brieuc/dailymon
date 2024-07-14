package com.brieuc.dailymon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brieuc.dailymon.service.AuthService;



@RestController
@RequestMapping(value = "/auth")
public class AuthController {

      private final AuthService authService;

      @Autowired
      public AuthController(AuthService authService) {
            this.authService = authService;
      }

      @PostMapping(value = "/login")
      public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
            return authService.login(authenticationRequest);
      }
}

