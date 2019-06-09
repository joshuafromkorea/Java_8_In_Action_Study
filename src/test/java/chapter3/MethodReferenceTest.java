package chapter3;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MethodReferenceTest {


    List<String> str = Arrays.asList("a","b","A","B");

    @Test
    public void 메서드_레퍼런스_만들기_테스트(){
        List<String> str1 = str;
        str1.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        List<String> str2 = str;
        str1.sort(String::compareToIgnoreCase);

        assertThat(str1.equals(str2));
    }
}
