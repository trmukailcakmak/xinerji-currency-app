package com.xinerji.currency;

import com.xinerji.currency.model.dto.auth.AuthRequest;
import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.auth.AuthResponse;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.user.UserRequest;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.entity.Role;
import com.xinerji.currency.model.entity.Users;
import com.xinerji.currency.model.type.RoleEnum;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.ArrayList;


public class TestDataPool {

    private final AuthenticationManager authenticationManager;

    public TestDataPool(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    public static AuthRequest createLoginRequest(){
        AuthRequest request = new AuthRequest();
        request.setUsername("tr.mukailcakmak@gmail.com");
        request.setPassword("111111");
        return request;
    }
    public static Role createRoleUser(){
        Role role = new Role();
        role.setName("USER");
        return role;
    }
    public static Role createRoleAdmin(){
        Role role = new Role();
        role.setName("ADMIN");
        return role;
    }

    public static UserRequest createSignUpRequest(){
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Mukail");
        userRequest.setSurname("Cakmak");
        userRequest.setProfession("Bilgisayar Programcılığı");
        userRequest.setCounty("Türkiye");
        userRequest.setCity("Istanbul");
        userRequest.setDistrict("Tuzla");
        userRequest.setEmail("tr.mukailcakmak@gmail.com");
        userRequest.setPhone("05385834543");
        userRequest.setPassword("111111");
        return userRequest;
    }
    public static UserResponse createUserResponse(){
        UserResponse response = new UserResponse();
        response.setName("Mukail");
        response.setSurname("Cakmak");
        response.setProfession("Bilgisayar Programcılığı");
        response.setCounty("Türkiye");
        response.setCity("Istanbul");
        response.setDistrict("Tuzla");
        response.setEmail("tr.mukailcakmak@gmail.com");
        response.setPhone("05385834543");
        response.setPassword("111111");
        return response;
    }
    public static UserResponseDto createUserResponseDto(){
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setName("Mukail");
        responseDto.setSurname("Cakmak");
        responseDto.setProfession("Bilgisayar Programcılığı");
        responseDto.setCounty("Türkiye");
        responseDto.setCity("Istanbul");
        responseDto.setDistrict("Tuzla");
        responseDto.setEmail("tr.mukailcakmak@gmail.com");
        responseDto.setPhone("05385834543");
        responseDto.setPassword("111111");
        return responseDto;
    }

    public static UserRequestDto createUserRequestDto(){
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setName("Mukail");
        requestDto.setSurname("Cakmak");
        requestDto.setProfession("Bilgisayar Programcılığı");
        requestDto.setCounty("Türkiye");
        requestDto.setCity("Istanbul");
        requestDto.setDistrict("Tuzla");
        requestDto.setEmail("tr.mukailcakmak@gmail.com");
        requestDto.setPhone("05385834543");
        requestDto.setPassword("1111");
        return requestDto;
    }
    public static RoleEnum createRoleEnumUser(){
        RoleEnum roleEnum = RoleEnum.USER;
        return roleEnum;
    }
    public static Users createUser(){
        Users entity = new Users();
        entity.setName("Mukail");
        entity.setSurname("Cakmak");
        entity.setProfession("Bilgisayar Programcılığı");
        entity.setCounty("Türkiye");
        entity.setCity("Istanbul");
        entity.setDistrict("Tuzla");
        entity.setEmail("tr.mukailcakmak@gmail.com");
        entity.setPhone("05385834543");
        entity.setPassword("111111");
        return entity;
    }

    public static AuthRequestDto createAuthRequestDto(){
        AuthRequestDto requestDto = new AuthRequestDto();
        requestDto.setUsername("tr.mukailcakmak@gmail.com");
        requestDto.setPassword("111111");
        return requestDto;
    }
    public static AuthResponseDto createAuthResponseDto(){
        AuthResponseDto responseDto = new AuthResponseDto("test-token","tr.mukailcakmak@gmail.com");
        return responseDto;
    }
    public static AuthResponse createAuthResponse(){
        AuthResponse response = new AuthResponse();
        response.setToken("test-token");
        response.setTokenType("Bearer ");
        response.setEmail("tr.mukailcakmak@gmail.com");
        return response;
    }
}
