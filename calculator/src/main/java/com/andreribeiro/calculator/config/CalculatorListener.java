package com.andreribeiro.calculator.config;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.andreribeiro.calculator.listener.model.RequestDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CalculatorListener {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorListener.class);

    @RabbitListener(queues = "calc.name.queue")
    public BigDecimal consumeNewOperation(final RequestDto operation) {
        logger.info("Performing Calculation: {}", operation);
        return performRequestCalculation(operation);
    }

    private BigDecimal performRequestCalculation(RequestDto operation) {
        BigDecimal result;
        switch (operation.getOperation()) {
        case SUM:
            result = operation.getFirstNumber().add(operation.getSecondNumber());
            break;
        case SUB:
            result = operation.getFirstNumber().subtract(operation.getSecondNumber());
            break;
        case DIV:
            result = operation.getFirstNumber().divide(operation.getSecondNumber(), 4, RoundingMode.HALF_UP);
            break;
        case MUL:
            result = operation.getFirstNumber().multiply(operation.getSecondNumber());
            break;
        default:
            return null;
        }
        logger.info("Calculation Result: {}", result);
        return result;
    }

}
