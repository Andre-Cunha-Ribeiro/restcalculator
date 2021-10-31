package com.andreribeiro.calculator.listener;

import java.math.BigDecimal;

import com.andreribeiro.rest.model.RequestDto;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorListener {


    @RabbitListener(queues = "tut.rpc.requests")
    public BigDecimal consumeNewOperation(final RequestDto operation) {
        //log.info("Consumed new operation: " + operation);
        return performRequestCalculation(operation);
    }

    private BigDecimal performRequestCalculation(RequestDto operation) {
        switch (operation.getOperation()) {
        case SUM:
            return operation.getFirstNumber().add(operation.getSecondNumber());
        case SUB:
            return operation.getFirstNumber().subtract(operation.getSecondNumber());
        case DIV:
            return operation.getFirstNumber().divide(operation.getSecondNumber());
        case MUL:
            return operation.getFirstNumber().multiply(operation.getSecondNumber());
        default:
            return null;
        }
    }

}
