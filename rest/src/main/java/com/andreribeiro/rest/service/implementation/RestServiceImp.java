package com.andreribeiro.rest.service.implementation;

import com.andreribeiro.rest.facade.MessageSenderFacade;
import com.andreribeiro.calculator.listener.model.RequestDto;
import com.andreribeiro.rest.service.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestServiceImp implements RestService{

    @Autowired
    private MessageSenderFacade messageSender;

    @Override
    public Object sendMessageAndReceive(RequestDto request) {
        return messageSender.sendMessageAndReceive(request);
        
    }
    
}
