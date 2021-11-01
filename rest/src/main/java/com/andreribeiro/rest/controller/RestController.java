package com.andreribeiro.rest.controller;

import java.math.BigDecimal;
import java.util.Collections;

import com.andreribeiro.calculator.listener.model.Operation;
import com.andreribeiro.calculator.listener.model.RequestDto;
import com.andreribeiro.rest.config.MessageSenderConfig;
import com.andreribeiro.rest.service.RestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private RestService service;

    @GetMapping(path = "/sum")
    public ResponseEntity<Object> getSumRequest(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        String id = MessageSenderConfig.generateUUID();
        RequestDto request = new RequestDto(Operation.SUM, a, b);
        return sendRequestAndReceiveResult(request, id);
    }

    @GetMapping(path = "/sub")
    public ResponseEntity<Object> getSubRequest(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        String id = MessageSenderConfig.generateUUID();
        RequestDto request = new RequestDto(Operation.SUB, a, b);
        return sendRequestAndReceiveResult(request, id);
    }

    @GetMapping(path = "/div")
    public ResponseEntity<Object> getDivRequest(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        if(b.compareTo(BigDecimal.ZERO) == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Collections.singletonMap("result", "Cannot divide by 0."));
        }
        String id = MessageSenderConfig.generateUUID();
        RequestDto request = new RequestDto(Operation.DIV, a, b);
        return sendRequestAndReceiveResult(request, id);
    }

    @GetMapping(path = "/mul")
    public ResponseEntity<Object> getMulRequest(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        String id = MessageSenderConfig.generateUUID();
        RequestDto request = new RequestDto(Operation.MUL, a, b);
        return sendRequestAndReceiveResult(request, id);
    }
    
    public ResponseEntity<Object> sendRequestAndReceiveResult(RequestDto request, String id){
        logger.info("Calculation Request Received");
        BigDecimal response = (BigDecimal) service.sendMessageAndReceive(request);
        logger.info("Calculation Request Completed. Result {}", response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(MessageSenderConfig.addResponseHeader(id)).body(Collections.singletonMap("result", response));
    }
    
    
}
