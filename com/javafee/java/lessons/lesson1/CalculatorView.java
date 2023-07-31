package com.javafee.java.lessons.lesson1;

public class CalculatorView {

    public void printOperationDetails(String operation, float firstNumber, float secondNumber, float result) {
        System.out.println(firstNumber
                + " "
                + operation
                + " "
                + secondNumber
                + " = "
                + result);
    }

    public void welcomeMenu() {
        String menu = "Welcome in Calculator application";
        System.out.println(menu);
    }

    public void putOperatorSign() {
        String operator =
                """
                        
                        Choose a correct operation character:
                        addition ->  +
                        subtraction -> -
                        multiplication -> *
                        division -> /
                        end of program -> exit
                        """;
        System.out.println(operator);
    }

    public void putFirstNumber() {
        String firstNumber = "give a first number";
        System.out.println(firstNumber);
    }

    public void putSecondNumber() {
        String secondNumber = "give a second number";
        System.out.println(secondNumber);
    }

    public void chooseWrongCharacter() {
        String wrongCharacter = "wrong character, choose once more";
        System.out.println(wrongCharacter);
    }

    public void chooseNotNumber() {
        String wrongNumber = "number is wrong or is not number, choose another number";
        System.out.println(wrongNumber);
    }
    public void chooseNotNumberOrZeroBySecondNumberByDivide() {
        String wrongNumber = "is not number or cannot divide by zero, choose another number";
        System.out.println(wrongNumber);
    }
}
