package com.andreribeiro.rest.service;

import com.andreribeiro.rest.model.RequestDto;

public interface RestService {
    public Object sendMessageAndReceive(RequestDto request);
}
