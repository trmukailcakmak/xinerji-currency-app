package com.xinerji.currency.model.dto.user;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String name;

    private String surname;

    private String profession;

    private String county;

    private String city;

    private String district;

    private String email;

    private String phone;

    private String password;
}
