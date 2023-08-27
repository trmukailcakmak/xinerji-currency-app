package com.xinerji.currency.controllers;

import com.xinerji.currency.constant.EndPointConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = EndPointConstant.TEST_CONTROLLER, produces={MediaType.APPLICATION_JSON_VALUE})
public interface TestController {

    @GetMapping()
    @ApiOperation("Token test")
    ResponseEntity<String> test();
}
