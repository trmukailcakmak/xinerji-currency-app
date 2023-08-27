package com.xinerji.currency.controllers.impl;

import com.xinerji.currency.controllers.TestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllerImpl implements TestController {

    @Autowired
    public TestControllerImpl() {
    }
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("this service run with token");
    }

}
