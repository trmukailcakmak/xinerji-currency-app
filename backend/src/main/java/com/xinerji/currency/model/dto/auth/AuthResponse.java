package com.xinerji.currency.model.dto.auth;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String tokenType;
    private String email;
}
