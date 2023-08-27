package com.xinerji.currency.controllers;

import com.xinerji.currency.constant.EndPointConstant;
import com.xinerji.currency.model.dto.auth.AuthResponse;
import com.xinerji.currency.model.dto.auth.AuthRequest;
import com.xinerji.currency.model.dto.user.UserRequest;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = EndPointConstant.AUTH_CONTROLLER, produces={MediaType.APPLICATION_JSON_VALUE})
public interface AuthController {
    @PostMapping(value = EndPointConstant.SIGN_IN)
    @ApiOperation("Sign in service")
    @ResponseBody ResponseEntity<AuthResponse> signIn(@RequestBody AuthRequest request);
    @PostMapping(value =EndPointConstant.SIGN_UP)
    @ApiOperation("Sign up service")
    ResponseEntity<UserResponse> signUp(@RequestBody UserRequest request);
}
