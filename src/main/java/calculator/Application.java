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
        String numbers = inputExpression;

        Pattern pattern = Pattern.compile("^//(.*?)(?:\n)(.*)$");
        Matcher matcher = pattern.matcher(inputExpression);

        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            numbers = matcher.group(2);

            if (customDelimiter.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
            }

            if (customDelimiter.matches("\\d")) {
                throw new IllegalArgumentException("숫자는 구분자로 사용할 수 없습니다.");
            }

            delimiter = defaultDelimiter + "|" + Pattern.quote(customDelimiter);

            if (customDelimiter.equals(" ")) {
                delimiter = defaultDelimiter + "|\\s+";
            }
        }

        return numbers.split(delimiter, -1);
    }

    private static int sum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            if (number.isEmpty()) {
                throw new IllegalArgumentException("구분자 뒤에는 숫자를 입력해야 합니다.");
            }

            if (!number.equals(number.trim())) {
                throw new IllegalArgumentException("숫자와 구분자 사이에 공백이 있으면 안 됩니다.");
            }

            if (!number.matches("^[1-9]\\d*$")) {
                throw new IllegalArgumentException("양의 정수만 입력 가능합니다.");
            }

            int parsedNumber = Integer.parseInt(number);
            sum += parsedNumber;
        }
        return sum;
    }
}
