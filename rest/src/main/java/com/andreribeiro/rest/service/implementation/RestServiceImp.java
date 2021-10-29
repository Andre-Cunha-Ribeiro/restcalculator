package com.andreribeiro.rest.service.implementation;

import com.andreribeiro.rest.facade.MessageSenderFacade;
import com.andreribeiro.rest.facade.implementation.MessageSenderFacadeImp;
import com.andreribeiro.rest.service.RestService;

import org.springframework.stereotype.Service;

@Service
public class RestServiceImp implements RestService{

    private MessageSenderFacade messageSender;

    RestServiceImp(){
        messageSender = new MessageSenderFacadeImp();
    }

    @Override
    public void sendMessage(String message) {
        messageSender.sendMessage(message);
        
    }
    
}
