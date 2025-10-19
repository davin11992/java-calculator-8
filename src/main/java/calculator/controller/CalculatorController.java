package calculator.controller;

import calculator.model.Calculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    private final Calculator calculator = new Calculator();

    public void run() {
        String inputExpression = InputView.readLine();
        int sum = calculator.calculate(inputExpression);
        OutputView.printSum(sum);
    }
}
