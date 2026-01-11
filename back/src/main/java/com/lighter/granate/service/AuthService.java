package com.lighter.granate.service;

import com.lighter.granate.dto.*;
import com.lighter.granate.entity.User;
import com.lighter.granate.exceptions.AppError;
import com.lighter.granate.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UsersService usersService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.usersService = usersService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> createUser(RegistrationUserDto dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Несовпадающие пароли"), HttpStatus.BAD_REQUEST);
        }
        if (usersService.readByUsername(dto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(), "Такой пользователь уже существует"), HttpStatus.CONFLICT);
        }
        User user = usersService.createUser(dto);
        return ResponseEntity.ok(new ViewUserDto(user.getId(), user.getUsername(), user.getEmail()));
    }

    private String getToken(JwtRequest jwtRequest) {
        UserDetails userDetails = usersService.loadUserByUsername(jwtRequest.getUsername());
        return  jwtUtil.generateToken(userDetails);
    }
    public ResponseEntity<?> getLoginResponse(JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неверный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        String token = getToken(jwtRequest);

        User user = usersService.readByUsername(jwtRequest.getUsername()).get();
        ViewUserDto viewDto = new ViewUserDto(user.getId(), user.getUsername(), user.getEmail());

        return ResponseEntity.ok(new LoginResponseDto(token, viewDto));
    }
}
