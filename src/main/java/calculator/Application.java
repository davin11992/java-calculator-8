package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String inputExpression = Console.readLine();

        if (inputExpression.trim().isEmpty()) {
            System.out.print("결과 : " + 0);
            return;
        }

        String[] numbers = splitByDelimiter(inputExpression);
        System.out.println("numbers = " + Arrays.toString(numbers));
    }

    private static String[] splitByDelimiter(String inputExpression) {
        String delimiter = ",|:";
        String numbers = inputExpression;

        if (inputExpression.startsWith("//")) {
            int delimiterEndIndex = inputExpression.indexOf('\n');
            delimiter = inputExpression.substring(2, delimiterEndIndex);
            numbers = inputExpression.substring(delimiterEndIndex + 1);
        }

        return numbers.split(delimiter);
    }
}
