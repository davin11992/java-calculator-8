package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 빈문자열_입력() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   "})
    void 공백_입력(String input) {
        assertSimpleTest(() -> {
            run(input);
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 기본_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2:3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 커스텀_구분자_사용2() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3;4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 커스텀_기본_구분자_혼용() {
        assertSimpleTest(() -> {
            run("//;\\n1;2,3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 커스텀_구분자가_공백인_경우() {
        assertSimpleTest(() -> {
            run("// \\n1 2 3 4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,2,3", "//;\\n0;2,3", "//;\\n-1;2,3", "0.1,0.2,0.3", "//;\\n0.1;0.2,0.3"})
    void 예외_0_음수_소수_입력(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("양의 정수만 입력 가능합니다.")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,!,3\"", "//;\\n0;!;3"})
    void 예외_문자_입력(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("양의 정수만 입력 가능합니다.")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,,2,3", "1,2,"})
    void 예외_구분자_뒤_빈무자열(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("구분자 뒤에는 숫자를 입력해야 합니다.")
        );
    }

    @Test
    void 예외_커스텀_구분자가_숫자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//1\\n1121314"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("숫자는 구분자로 사용할 수 없습니다.")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;!\\n1;!2;!3;!4", "//  \\n1  2  3  4"})
    void 예외_커스텀_구분자가_두글자_이상(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("커스텀 구분자는 한 글자여야 합니다.")
        );
    }


    @ParameterizedTest
    @ValueSource(strings = {"//;\\n1;2 , 3:4 ", "//;\\n1;2 , 3: "})
    void 예외_숫자와_구분자_사이_공백(String input) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("숫자와 구분자 사이에 공백이 있으면 안 됩니다.")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
