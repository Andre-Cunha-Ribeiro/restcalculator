package com.andreribeiro.rest.facade.implementation;

import com.andreribeiro.rest.facade.MessageSenderFacade;
import com.andreribeiro.calculator.listener.model.RequestDto;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderFacadeImp implements MessageSenderFacade{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @Override
    public Object sendMessageAndReceive(RequestDto request){
        return rabbitTemplate.convertSendAndReceive(exchange.getName(), "rpc", request);
    }

}
