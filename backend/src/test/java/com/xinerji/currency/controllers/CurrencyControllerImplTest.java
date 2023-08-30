package com.xinerji.currency.controllers;

import com.google.gson.Gson;
import com.xinerji.currency.TestDataPool;
import com.xinerji.currency.constant.EndPointConstant;
import com.xinerji.currency.controllers.impl.CurrencyControllerImpl;
import com.xinerji.currency.model.dto.currency.CurrencyRequest;
import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponse;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.mapper.CurrencyMapper;
import com.xinerji.currency.service.CurrencyService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

public class CurrencyControllerImplTest {
    private final Gson gson = new Gson();

    @InjectMocks
    private CurrencyControllerImpl currencyControllerImpl;
    @Mock
    private CurrencyService currencyService;

    @Mock
    private final CurrencyMapper currencyMapper = CurrencyMapper.INSTANCE;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(currencyControllerImpl).build();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetCurrencyRateByDate() throws Exception {
        CurrencyRequest request = TestDataPool.createCurrencyRequest();
        CurrencyRequestDto requestDto = TestDataPool.createCurrencyRequestDto();
        List<CurrencyResponseDto> responseDto = TestDataPool.createCurrencyResponseDtoList();
        List<CurrencyResponse> responseServiceData = TestDataPool.createCurrencyResponseList();

        Mockito.when(currencyMapper.mapRequestToRequestDto(Mockito.any(CurrencyRequest.class))).thenReturn(requestDto);
        Mockito.when(currencyService.getCurrencyRateByDate(Mockito.any(CurrencyRequestDto.class))).thenReturn(responseDto);
        Mockito.when(currencyMapper.mapResponseDtoListToResponseList(Mockito.any())).thenReturn(responseServiceData);

        ResponseEntity<List<CurrencyResponse>> responseEntity = currencyControllerImpl.getCurrencyRateByDate(request);

        Assert.assertNotNull(responseEntity);
    }



}