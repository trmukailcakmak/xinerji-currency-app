package com.xinerji.currency.controllers;

import com.xinerji.currency.constant.EndPointConstant;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = EndPointConstant.USER_CONTROLLER, produces={MediaType.APPLICATION_JSON_VALUE})
public interface UserController {

    /*@PostMapping(value = EndPointConstant.SIGN_UP_ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("Admin kayit servisi")
    ResponseEntity<UserDto> signUpAdmin(@RequestBody UserRequest userRequest);*/
    @GetMapping(value = EndPointConstant.GET_USER_INFO)
    @ApiOperation("User Bilgisi getiren servis")
    ResponseEntity<UserResponse> getUserInfo(@PathVariable String key);
    @GetMapping(value = EndPointConstant.GET_USER_BY_ID)
    ResponseEntity<UserResponse> findUserById(@PathVariable Long id);
}
