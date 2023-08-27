package com.xinerji.currency.constant;

import org.springframework.stereotype.Component;

@Component
public class EndPointConstant {
    // class
    public static final String API = "/api";
    public static final String AUTH = "/auth";
    public static final String USER = "/user";
    public static final String CURRENCY = "/currency";
    public static final String TEST = "/test";
    public static final String AUTH_CONTROLLER = API + AUTH;
    public static final String USER_CONTROLLER = API + USER;
    public static final String CURRENCY_CONTROLLER = API + CURRENCY;
    public static final String TEST_CONTROLLER = API + TEST;

    // Auth
    public static final String SIGN_IN = "/signIn";
    public static final String SIGN_UP = "/signUp";
    public static final String GET_USER_INFO = "/user/{key}";

    // User
    public static final String SIGN_UP_ADMIN = "/signUp/admin";
    public static final String GET_USER_BY_ID = "/{id}";

    // Currency
    public static final String GET_CURRENCY_RATE_BY_DATE = "/getCurrencyRateByDate";





}
