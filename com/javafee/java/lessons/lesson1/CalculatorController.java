package com.javafee.java.lessons.lesson1;

import java.util.List;
import java.util.Scanner;

public class CalculatorController {

    private static final List<String> operationsCharacters = List.of("+", "-", "*", "/");

    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController() {
    }

    public void startingApplication() {
        model = new CalculatorModel();
        view = new CalculatorView();

        view.welcomeMenu();

        while (true) {
            view.putOperatorSign();
            Scanner scanner = new Scanner(System.in);
            model.setOperation(scanner.nextLine());
            while (!CalculatorController.operationsCharacters.contains(model.getOperation())) {
                view.chooseWrongCharacter();
                model.setOperation(scanner.nextLine());
            }

            view.putFirstNumber();
            String firstNumberString = scanner.next();
            while(!isValidFloatNumberInString(firstNumberString)){
                view.chooseNotNumber();
                firstNumberString = scanner.next();
            }
            model.setFirstNumber(Float.parseFloat(firstNumberString));


            view.putSecondNumber();
            String secondNumberString = scanner.next();
            while(!isValidFloatNumberInString(secondNumberString)
                    || isValidDivideByNotZero(getModel().getOperation(), getModel().getSecondNumber())){
                view.chooseNotNumberOrZeroBySecondNumberByDivide();
                secondNumberString = scanner.next();
            }
            model.setSecondNumber(Float.parseFloat(secondNumberString));


            float result = CalculatorModel.doMathOperation(model.getOperation(), model.getFirstNumber(), model.getSecondNumber());
            view.printOperationDetails(model.getOperation(), model.getFirstNumber(), model.getSecondNumber(), result);

        }

    }

    public static boolean isValidFloatNumberInString(String number){
        return number.matches("[+-]?([0-9]+[.])?[0-9]+");
    }

    public static boolean isValidDivideByNotZero(String operation, float secondNumber){
        return operation.equals("/") && secondNumber == 0;
    }


    public CalculatorModel getModel() {
        return model;
    }

    public void setModel(CalculatorModel model) {
        this.model = model;
    }

    public CalculatorView getView() {
        return view;
    }

    public void setView(CalculatorView view) {
        this.view = view;
    }
}
