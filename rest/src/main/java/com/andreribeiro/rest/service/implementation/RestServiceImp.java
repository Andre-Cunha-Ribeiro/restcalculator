package com.andreribeiro.rest.service.implementation;

import com.andreribeiro.rest.facade.MessageSenderFacade;
import com.andreribeiro.rest.facade.implementation.MessageSenderFacadeImp;
import com.andreribeiro.rest.service.RestService;
import com.andreribeiro.calculator.listener.model.RequestDto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestServiceImp implements RestService{

    @Autowired
    private MessageSenderFacade messageSender;

    public RestServiceImp(){
        messageSender = new MessageSenderFacadeImp();
    }

    @Override
    public Object sendMessageAndReceive(RequestDto request) {
        return messageSender.sendMessageAndReceive(request);
        
    }
    
}
