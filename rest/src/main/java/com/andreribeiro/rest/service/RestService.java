package com.andreribeiro.rest.service;

import com.andreribeiro.calculator.listener.model.RequestDto;

public interface RestService {
    public Object sendMessageAndReceive(RequestDto request);
}
