package com.andreribeiro.rest.facade;

import com.andreribeiro.rest.model.RequestDto;

public interface MessageSenderFacade{
    public Object sendMessageAndReceive(RequestDto request);
}