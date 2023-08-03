package com.javafee.java.lessons.lesson1;


public class CalculatorModel {

    private String operation;
    private float firstNumber;
    private float secondNumber;

    public static float doMathOperation(String operation, float firstNumber, float secondNumber) {
        return switch (operation) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            default -> throw new UnsupportedOperationException("Invalid operation: " + operation);
        };
    }

    public String saveOperationDetails(String operation, float firstNumber, float secondNumber, float result) {
        return firstNumber
                + " "
                + operation
                + " "
                + secondNumber
                + " = "
                + result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public float getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(float firstNumber) {
        this.firstNumber = firstNumber;
    }

    public float getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(float secondNUmber) {
        this.secondNumber = secondNUmber;
    }
}
