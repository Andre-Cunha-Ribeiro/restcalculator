package com.andreribeiro.calculator.listener.model;

public enum Operation {
    SUM('+'), SUB('-'), MUL('*'), DIV('%');

    private Character character;
 
    Operation(Character character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return character.toString();
    }

    
 

}
