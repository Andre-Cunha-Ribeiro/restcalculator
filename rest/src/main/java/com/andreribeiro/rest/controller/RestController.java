package com.andreribeiro.rest.controller;

import java.math.BigDecimal;
import java.util.Collections;

import com.andreribeiro.rest.facade.implementation.AspectConfig;
import com.andreribeiro.calculator.listener.model.Operation;
import com.andreribeiro.calculator.listener.model.RequestDto;
import com.andreribeiro.rest.service.RestService;
import com.rabbitmq.client.RpcClient.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private RestService service;

    // public RestController() {
    //     service = new RestServiceImp();
    // }

    public ResponseEntity<Object> produceNewOperation(RequestDto request){
        //log.info("Produced new operation: " + op);
        logger.info("Calculation received");
        BigDecimal response = (BigDecimal) service.sendMessageAndReceive(request);
        //BigDecimal response = (String)service.sendMessageAndReceive(request);

        logger.info("Calculation completed, response=" + response);
        
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("id", AspectConfig.get);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("result", response));
    }

    @GetMapping(path = "/sum")
    public ResponseEntity<Object> getSumRequest(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        RequestDto request = new RequestDto(Operation.SUM, a, b);
        return produceNewOperation(request);
    }

    @GetMapping(path = "/sub")
    public ResponseEntity<Object> getSubRequest(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        RequestDto request = new RequestDto(Operation.SUB, a, b);
        return produceNewOperation(request);
    }

    @GetMapping(path = "/div")
    public ResponseEntity<Object> getDivRequest(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        RequestDto request = new RequestDto(Operation.DIV, a, b);
        return produceNewOperation(request);
    }

    @GetMapping(path = "/mul")
    public ResponseEntity<Object> getMulRequest(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        RequestDto request = new RequestDto(Operation.MUL, a, b);
        return produceNewOperation(request);
    }

    
}
