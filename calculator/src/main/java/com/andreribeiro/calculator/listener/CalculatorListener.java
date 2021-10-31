package com.andreribeiro.calculator.listener;

import java.math.BigDecimal;

import com.andreribeiro.calculator.listener.model.RequestDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorListener {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorListener.class);


    @RabbitListener(queues = "tut.rpc.requests")
    public BigDecimal consumeNewOperation(final RequestDto operation) {
        logger.info("Performing Calculation: " + operation);
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
        //logger.info("Return Calculation: " + operation);
     }

}
