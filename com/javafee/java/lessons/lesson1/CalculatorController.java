package com.javafee.java.lessons.lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculatorController {

    private static final List<String> OPERATIONS_CHARACTERS = List.of("+", "-", "*", "/");
    private static final String EXIT_FROM_APP = "exit";
    private static final String SHOW_LAST_OPERATIONS = "show";
    private final List<String> lastResults = new ArrayList<>();

    private CalculatorModel model;
    private CalculatorView view;


    public void startApplication() {
        model = new CalculatorModel();
        view = new CalculatorView();

        view.welcomeMenu();

        model.setOperation("");

        view.putOperatorSign();
        Scanner scanner = new Scanner(System.in);
        model.setOperation(scanner.nextLine());
        while (!(model.getOperation()).equalsIgnoreCase(EXIT_FROM_APP)) {

            if (!model.getOperation().equalsIgnoreCase(SHOW_LAST_OPERATIONS)) {
                while (!CalculatorController.OPERATIONS_CHARACTERS.contains(model.getOperation())) {
                    view.chooseWrongCharacter();
                    model.setOperation(scanner.next());
                }


                view.putFirstNumber();
                String firstNumberString = scanner.next();
                while (isValidFloatNumberInString(firstNumberString)) {
                    view.chooseNotNumber();
                    firstNumberString = scanner.next();
                }
                model.setFirstNumber(Float.parseFloat(firstNumberString));


                view.putSecondNumber();
                String secondNumberString = scanner.next();
                while (isValidFloatNumberInString(secondNumberString) || isValidWhenDivideByNotZero(model.getOperation(),
                        Float.parseFloat(secondNumberString))) {
                    view.chooseNotNumberOrZeroBySecondNumberByDivide();
                    secondNumberString = scanner.next();
                }
                model.setSecondNumber(Float.parseFloat(secondNumberString));


                float result = CalculatorModel.doMathOperation(model.getOperation(),
                        model.getFirstNumber(),
                        model.getSecondNumber());
                view.printOperationDetails(model.getOperation(),
                        model.getFirstNumber(),
                        model.getSecondNumber(),
                        result);

                String saveResult = model.saveOperationDetails(model.getOperation(),
                        model.getFirstNumber(),
                        model.getSecondNumber(),
                        result);

                lastResults.add(saveResult);
            } else for (String x : lastResults) System.out.println(x);


            view.putOperatorSign();
            model.setOperation(scanner.next());
        }

    }

    public static boolean isValidFloatNumberInString(String number) {
        return !number.matches("[+-]?([0-9]+[.])?[0-9]+");
    }

    public static boolean isValidWhenDivideByNotZero(String operation, float secondNumber) {
        return operation.equals("/") && secondNumber == 0;
    }
}
