package com.xinerji.currency.controllers;

import com.google.gson.Gson;
import com.xinerji.currency.constant.EndPointConstant;
import com.xinerji.currency.controllers.impl.TestControllerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class TestControllerImplTest {
    private final Gson gson = new Gson();

    @InjectMocks
    private TestControllerImpl testControllerImpl;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(testControllerImpl).build();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTest() throws Exception {
        String endPoint = EndPointConstant.TEST_CONTROLLER;
        // istek yapılıyor
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(endPoint))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        // cevap alınıyor
        MockHttpServletResponse response = result.getResponse();
        String responseMessage = response.getContentAsString();
        // doğrulamalar yapılıyor
        Assert.assertNotNull(responseMessage);
    }



}