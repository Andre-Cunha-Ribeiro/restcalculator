package com.andreribeiro.rest.facade;

import com.andreribeiro.calculator.listener.model.RequestDto;

public interface MessageSenderFacade{
    public Object sendMessageAndReceive(RequestDto request);
}