package com.xinerji.currency.service.impl;

import com.xinerji.currency.TestDataPool;
import com.xinerji.currency.model.dto.currency.CurrencyRequestDto;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.mapper.CurrencyMapper;
import com.xinerji.currency.service.RestTemplateService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

public class CurrencyServiceImplTest {
    @InjectMocks
    private CurrencyServiceImpl currencyServiceImpl;
    @Mock
    private final CurrencyMapper currencyMapper = CurrencyMapper.INSTANCE;

    @Mock
    private RestTemplateService restTemplateService;

    @Mock
    private MessageSource messageSource;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(currencyServiceImpl).build();
    }

    @After
    public void tearDown() {
    };

    @Test
    public void testGetCurrencyRateByDate() {
        CurrencyRequestDto requestDto = TestDataPool.createCurrencyRequestDto();
        List<CurrencyResponseDto> responseDtoList = TestDataPool.createCurrencyResponseDtoList();

        Mockito.when(restTemplateService.callCurrencyServiceByDate(Mockito.any(LocalDateTime.class))).thenReturn(responseDtoList);

        List<CurrencyResponseDto> responseDto = currencyServiceImpl.getCurrencyRateByDate(requestDto);

        Assert.assertNotNull(responseDto);
    }

}