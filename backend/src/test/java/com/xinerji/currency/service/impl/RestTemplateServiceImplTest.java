package com.xinerji.currency.service.impl;


import com.xinerji.currency.model.mapper.CurrencyMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@RunWith(MockitoJUnitRunner.class)
public class RestTemplateServiceImplTest {
    @InjectMocks
    private RestTemplateServiceImpl restTemplateServiceImpl;

    @Mock
    private final CurrencyMapper currencyMapper = CurrencyMapper.INSTANCE;

    @Mock
    private MessageSource messageSource;

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity responseEntity;

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCallCurrencyServiceByDate() {
        // toDO: UNIT TEST WILL BE WRİTE
    }

}