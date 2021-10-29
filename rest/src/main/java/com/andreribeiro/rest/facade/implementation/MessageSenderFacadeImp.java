package com.andreribeiro.rest.facade.implementation;

import com.andreribeiro.rest.facade.MessageSenderFacade;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderFacadeImp implements MessageSenderFacade{

    private RabbitTemplate rabbit;

    @Value("${rabbit.queue.name}")
    private String queueName;

    public MessageSenderFacadeImp() {
        rabbit = new RabbitTemplate();
    }

    @Override
    public void sendMessage(String message) {
        rabbit.convertAndSend(queueName, message);
        
    }

}
