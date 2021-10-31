package com.andreribeiro.calculator.listener.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RequestDto implements Serializable{
    private static final long serialVersionUID = 1L;
    Operation operation;
    BigDecimal firstNumber;
    BigDecimal secondNumber;

    public RequestDto(Operation operation, BigDecimal firstNumber, BigDecimal secondNumber) {
        this.operation = operation;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }

    @Override
    public String toString() {
        return "RequestDto [firstNumber=" + firstNumber + ", operation=" + operation + ", secondNumber=" + secondNumber
                + "]";
    }

    

    
}
