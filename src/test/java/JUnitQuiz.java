import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JUnitQuiz {

    @Test
    public void JunitQuizTest(){
        String name1 = "Hong";
        String name2 = "Hong";
        String name3 = "dong";

        //모든 변수가 NotNull인지 확인
        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();

        assertThat(name1).isNotEqualTo(name2);

        int num1 = 1;
        int num2 = 2;
        int num3 = 3;
        assertThat(num1).isGreaterThan(num2);
        assertThat(num1).isLessThan(num2);
    }

}
