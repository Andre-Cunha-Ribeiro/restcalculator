package com.andreribeiro.rest.controller;

import java.math.BigDecimal;
import java.util.Collections;

import com.andreribeiro.rest.model.Operation;
import com.andreribeiro.rest.model.RequestDto;
import com.andreribeiro.rest.service.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private RestService service;

    //private static final Logger log =  LogManager.getLogger(RestController.class);


    // public RestController() {
    //     service = new RestServiceImp();
    // }

    public ResponseEntity<Object> produceNewOperation(RequestDto request){
        //log.info("Produced new operation: " + op);
    
        BigDecimal response = (BigDecimal) service.sendMessageAndReceive(request);
    
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
