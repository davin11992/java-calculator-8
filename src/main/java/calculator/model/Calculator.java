package calculator.model;

public class Calculator {
    private final NumberParser numberParser = new NumberParser();

    public int calculate(String inputExpression) {
        if (isEmptyInput(inputExpression)) {
            return 0;
        }
        String[] numbers = numberParser.splitByDelimiter(inputExpression);
        return sum(numbers);
    }

    private static boolean isEmptyInput(String inputExpression) {
        return inputExpression == null || inputExpression.trim().isEmpty();
    }

    private int sum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            validateNumber(number);
            sum += Integer.parseInt(number);
        }
        return sum;
    }

    private static void validateNumber(String number) {
        if (number.isEmpty()) {
            throw new IllegalArgumentException("구분자 뒤에는 숫자를 입력해야 합니다.");
        }
        if (!number.equals(number.trim())) {
            throw new IllegalArgumentException("숫자와 구분자 사이에 공백이 있으면 안 됩니다.");
        }
        if (!number.matches("^[1-9]\\d*$")) {
            throw new IllegalArgumentException("양의 정수만 입력 가능합니다.");
        }
    }
}