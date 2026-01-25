package com.lighter.granate.controller;

import com.lighter.granate.dto.JwtRequest;
import com.lighter.granate.dto.RegistrationUserDto;
import com.lighter.granate.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationUserDto dto) {
        return authService.createUser(dto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) {
        return authService.getLoginResponse(jwtRequest);
    }

    @GetMapping("/valid")
    public ResponseEntity<?> isValid() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> isAdmin() { return new ResponseEntity<>(true, HttpStatus.OK); }

}
