package com.lighter.granate.dto;

import com.lighter.granate.entity.User;
import lombok.Data;

@Data
public class LoginResponseDto {
    public String token;
    public ViewUserDto user;

    public LoginResponseDto(String token, ViewUserDto user) {
        this.token = token;
        this.user = user;
    }
}
