package calculator.model;

public class Calculator {
    private final NumberParser numberParser = new NumberParser();

    public int calculate(String inputExpression) {
        if (InputValidator.isEmptyInput(inputExpression)) {
            return 0;
        }
        String[] numbers = numberParser.splitByDelimiter(inputExpression);
        return sum(numbers);
    }

    private int sum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            NumberValidator.validateNumber(number);
            sum += Integer.parseInt(number);
        }
        return sum;
    }
}