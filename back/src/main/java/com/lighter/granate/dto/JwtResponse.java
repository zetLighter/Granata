package com.lighter.granate.dto;

import lombok.Data;


@Data
public class JwtResponse {
    public String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
