package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String inputExpression = Console.readLine();

        if (inputExpression == null || inputExpression.trim().isEmpty()) {
            System.out.print("결과 : " + 0);
            return;
        }

        String[] numbers = splitByDelimiter(inputExpression);
        System.out.print("결과 : " + sum(numbers));
    }

    private static String[] splitByDelimiter(String inputExpression) {
        inputExpression = inputExpression.replace("\\n", "\n");

        String defaultDelimiter = ",|:";
        String delimiter = defaultDelimiter;
        String numbers = inputExpression; //trim()해야되나?

        Pattern pattern = Pattern.compile("^//(.*?)(?:\n)(.*)$");
        Matcher matcher = pattern.matcher(inputExpression);

        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            numbers = matcher.group(2);
            delimiter = defaultDelimiter + "|" + Pattern.quote(customDelimiter);
        }

        return numbers.split(delimiter);
    }

    private static int sum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            int value = Integer.parseInt(number.trim());
            if (value < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다. " + value);
            }
            sum += value;
        }
        return sum;
    }
}
