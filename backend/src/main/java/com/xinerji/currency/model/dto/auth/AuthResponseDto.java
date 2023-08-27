package com.xinerji.currency.model.dto.auth;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String tokenType = "Bearer ";
    private String email;

    public AuthResponseDto(String token,String email) {
        this.token = token;
        this.email = email;
    }
}
