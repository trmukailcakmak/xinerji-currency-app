package com.xinerji.currency.controllers;

import com.google.gson.Gson;
import com.xinerji.currency.TestDataPool;
import com.xinerji.currency.constant.EndPointConstant;
import com.xinerji.currency.controllers.impl.AuthControllerImpl;
import com.xinerji.currency.model.dto.auth.AuthRequest;
import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.auth.AuthResponse;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.user.UserRequest;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.mapper.LoginMapper;
import com.xinerji.currency.model.mapper.UserMapper;
import com.xinerji.currency.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AuthControllerImplTest {
    private final Gson gson = new Gson();
    @InjectMocks
    private AuthControllerImpl authControllerImpl;
    @Mock
    private final LoginMapper loginMapper = LoginMapper.INSTANCE;
    @Mock
    private final UserMapper userMapper = UserMapper.INSTANCE;
    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authControllerImpl).build();
    }

    @After
    public void tearDown() {
    }
    @Test
    public void testSignIn() throws Exception {
        AuthRequest request = TestDataPool.createLoginRequest();
        AuthRequestDto requestDto = TestDataPool.createAuthRequestDto();
        AuthResponseDto responseDto = TestDataPool.createAuthResponseDto();
        AuthResponse responseServiceData = TestDataPool.createAuthResponse();

        String requestBodyJson = gson.toJson(request);
        String endPoint = EndPointConstant.AUTH_CONTROLLER + EndPointConstant.SIGN_IN;
        Mockito.when(loginMapper.mapRequestToRequestDto(Mockito.any(AuthRequest.class))).thenReturn(requestDto);
        Mockito.when(userService.login(Mockito.any(AuthRequestDto.class))).thenReturn(responseDto);
        Mockito.when(loginMapper.mapResposeDtoToResponse(Mockito.any(AuthResponseDto.class))).thenReturn(responseServiceData);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(endPoint).content(requestBodyJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String responseMessage = response.getContentAsString();
        AuthResponse responseService = gson.fromJson(responseMessage,AuthResponse.class);

        Assert.assertNotNull(responseService);
        Assert.assertEquals(request.getUsername(), responseService.getEmail());
        Assert.assertEquals(responseDto.getToken(), responseService.getToken());
        Assert.assertEquals(responseDto.getTokenType(), responseService.getTokenType());
        Assert.assertEquals(responseDto.getEmail(), responseService.getEmail());
        Mockito.verify(userService, Mockito.times(1)).login(requestDto);
    }

    @Test
    public void testSignUp() throws Exception {
        UserRequest request = TestDataPool.createSignUpRequest();
        UserRequestDto requestDto = TestDataPool.createUserRequestDto();
        UserResponseDto responseDto = TestDataPool.createUserResponseDto();
        UserResponse responseServiceData = TestDataPool.createUserResponse();

        String requestBodyJson = gson.toJson(request);
        String endPoint = EndPointConstant.AUTH_CONTROLLER + EndPointConstant.SIGN_UP;
        Mockito.when(userMapper.mapRequestToRequestDto(Mockito.any(UserRequest.class))).thenReturn(requestDto);
        Mockito.when(userService.register(Mockito.any(UserRequestDto.class))).thenReturn(responseDto);
        Mockito.when(userMapper.mapResponseDtoToResponse(Mockito.any(UserResponseDto.class))).thenReturn(responseServiceData);


        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(endPoint).content(requestBodyJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String responseMessage = response.getContentAsString();
        UserResponse responseService = gson.fromJson(responseMessage,UserResponse.class);

        Assert.assertNotNull(responseService);
        Assert.assertEquals(request.getEmail(), responseService.getEmail());
        Assert.assertEquals(request.getPhone(), responseService.getPhone());
        Assert.assertEquals(request.getName(), responseService.getName());
    }


}