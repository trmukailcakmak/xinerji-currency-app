package com.xinerji.currency;

import com.xinerji.currency.model.dto.auth.AuthRequest;
import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.auth.AuthResponse;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.currency.CurrencyRequest;
import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponse;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.dto.user.UserRequest;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.entity.Role;
import com.xinerji.currency.model.entity.Users;
import com.xinerji.currency.model.type.RoleEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class TestDataPool {

    private final AuthenticationManager authenticationManager;

    public TestDataPool(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    private static String test = "test";
    public static CurrencyRequest createCurrencyRequest(){
        CurrencyRequest request = new CurrencyRequest();
        request.setDate(LocalDateTime.now());
        return request;
    }
    public static CurrencyRequestDto createCurrencyRequestDto(){
        CurrencyRequestDto request = new CurrencyRequestDto();
        request.setDate(LocalDateTime.now());
        return request;
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

    public static LocalDateTime createRestTemplateRequestDto(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenDaysAgo = now.minus(10, ChronoUnit.DAYS);
        return tenDaysAgo;
    }
    public static AuthResponseDto createAuthResponseDto(){
        AuthResponseDto responseDto = new AuthResponseDto("test-token","tr.mukailcakmak@gmail.com");
        return responseDto;
    }
    public static CurrencyResponseDto createCurrencyResponseDto(){
        CurrencyResponseDto responseDto = new CurrencyResponseDto();
        responseDto.setIsim(test);
        responseDto.setCurrencyName(test);
        responseDto.setForexBuying(test);
        responseDto.setForexSelling(test);
        responseDto.setBanknoteBuying(test);
        responseDto.setBanknoteSelling(test);
        responseDto.setCrossRateUSD(test);
        responseDto.setCrossRateOther(test);

        return responseDto;
    }
    public static List<CurrencyResponseDto> createCurrencyResponseDtoList(){
        List<CurrencyResponseDto> responseDtoList = new ArrayList<>();
        CurrencyResponseDto responseDto = new CurrencyResponseDto();
        responseDto.setIsim(test);
        responseDto.setCurrencyName(test);
        responseDto.setForexBuying(test);
        responseDto.setForexSelling(test);
        responseDto.setBanknoteBuying(test);
        responseDto.setBanknoteSelling(test);
        responseDto.setCrossRateUSD(test);
        responseDto.setCrossRateOther(test);

        responseDtoList.add(responseDto);
        return responseDtoList;
    }
    public static ResponseEntity<Object> createRestTemplateForCallCurrencyResponseList(){
        String response = "<?xml version=1.0 encoding=UTF-8?>" +
                "<?xml-stylesheet type=text/xsl href=isokur.xsl?>" +
                "<Tarih_Date Tarih=29.08.2023 Date=08/29/2023  Bulten_No=2023/164 >" +
                "    <Currency CrossOrder=0 Kod=USD CurrencyCode=USD>" +
                "        <Unit>1</Unit>" +
                "        <Isim>ABD DOLARI</Isim>" +
                "        <CurrencyName>US DOLLAR</CurrencyName>" +
                "        <ForexBuying>26.5763</ForexBuying>" +
                "        <ForexSelling>26.6242</ForexSelling>" +
                "        <BanknoteBuying>26.5577</BanknoteBuying>" +
                "        <BanknoteSelling>26.6641</BanknoteSelling>" +
                "        <CrossRateUSD/>" +
                "        <CrossRateOther/>" +
                "    </Currency>" +
                "</Tarih_Date>";
        return ResponseEntity.ok(response);
    }
    public static List<CurrencyResponseDto> createRestTemplateResponseDtoList(){
        List<CurrencyResponseDto> responseDtoList = new ArrayList<>();
        CurrencyResponseDto responseDto = new CurrencyResponseDto();
        responseDto.setIsim(test);
        responseDto.setCurrencyName(test);
        responseDto.setForexBuying(test);
        responseDto.setForexSelling(test);
        responseDto.setBanknoteBuying(test);
        responseDto.setBanknoteSelling(test);
        responseDto.setCrossRateUSD(test);
        responseDto.setCrossRateOther(test);

        responseDtoList.add(responseDto);
        return responseDtoList;
    }
    public static CurrencyResponse createCurrencyResponse(){
        CurrencyResponse response = new CurrencyResponse();
        response.setIsim(test);
        response.setCurrencyName(test);
        response.setForexBuying(test);
        response.setForexSelling(test);
        response.setBanknoteBuying(test);
        response.setBanknoteSelling(test);
        response.setCrossRateUSD(test);
        response.setCrossRateOther(test);

        return response;
    }
    public static List<CurrencyResponse> createCurrencyResponseList(){
        List<CurrencyResponse> responseList = new ArrayList<>();
        CurrencyResponse response = new CurrencyResponse();
        response.setIsim(test);
        response.setCurrencyName(test);
        response.setForexBuying(test);
        response.setForexSelling(test);
        response.setBanknoteBuying(test);
        response.setBanknoteSelling(test);
        response.setCrossRateUSD(test);
        response.setCrossRateOther(test);

        responseList.add(response);
        return responseList;
    }
    public static AuthResponse createAuthResponse(){
        AuthResponse response = new AuthResponse();
        response.setToken("test-token");
        response.setTokenType("Bearer ");
        response.setEmail("tr.mukailcakmak@gmail.com");
        return response;
    }
}
