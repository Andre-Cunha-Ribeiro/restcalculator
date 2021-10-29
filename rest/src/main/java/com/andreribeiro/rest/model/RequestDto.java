package com.andreribeiro.rest.model;

import java.math.BigDecimal;

public class RequestDto {
    Operation operation;
    BigDecimal firstNumber;
    BigDecimal secondNumber;

    public RequestDto(Operation operation, BigDecimal firstNumber, BigDecimal secondNumber) {
        this.operation = operation;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }
}
