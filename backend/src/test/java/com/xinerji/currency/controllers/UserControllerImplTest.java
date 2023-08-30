package com.xinerji.currency.controllers;

import com.google.gson.Gson;
import com.xinerji.currency.TestDataPool;
import com.xinerji.currency.constant.EndPointConstant;
import com.xinerji.currency.controllers.impl.UserControllerImpl;
import com.xinerji.currency.model.dto.user.UserResponse;
import com.xinerji.currency.model.dto.user.UserResponseDto;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerImplTest {
    private final Gson gson = new Gson();
    @InjectMocks
    private UserControllerImpl userControllerImpl;
    @Mock
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userControllerImpl).build();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetUserInfo() throws Exception {
        String key = "test-key";
        UserResponseDto responseDto = TestDataPool.createUserResponseDto();
        UserResponse responseServiceData = TestDataPool.createUserResponse();

        // user servisi mock'ı
        Mockito.when(userService.getUserInfo(Mockito.any())).thenReturn(responseDto);
        Mockito.when(userMapper.mapResponseDtoToResponse(Mockito.any(UserResponseDto.class))).thenReturn(responseServiceData);
        String endPoint = EndPointConstant.USER_CONTROLLER + EndPointConstant.GET_USER_INFO;

        // istek yapılıyor
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(endPoint, key))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // cevap alınıyor
        MockHttpServletResponse response = result.getResponse();
        String responseMessage = response.getContentAsString();
        UserResponse responseService = new Gson().fromJson(responseMessage, UserResponse.class);
        // doğrulamalar yapılıyor
        Assert.assertNotNull(responseService);
    }

    @Test
    public void testfindUserById() throws Exception {
        Long id = 1l;
        UserResponseDto responseDto = TestDataPool.createUserResponseDto();
        UserResponse responseServiceData = TestDataPool.createUserResponse();
        // user servisi mock'ı
        Mockito.when(userService.findUserById(Mockito.anyLong())).thenReturn(responseDto);
        Mockito.when(userMapper.mapResponseDtoToResponse(Mockito.any(UserResponseDto.class))).thenReturn(responseServiceData);
        String endPoint = EndPointConstant.USER_CONTROLLER + EndPointConstant.GET_USER_BY_ID;
        // istek yapılıyor
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(endPoint, id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        // cevap alınıyor
        MockHttpServletResponse response = result.getResponse();
        String responseMessage = response.getContentAsString();
        UserResponse responseService = gson.fromJson(responseMessage, UserResponse.class);
        // doğrulamalar yapılıyor
        Assert.assertNotNull(responseService);
    }



}