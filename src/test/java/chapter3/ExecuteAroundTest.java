package chapter3;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;

@SpringBootTest
public class ExecuteAroundTest {

    private ExecuteAround executeAround = new ExecuteAround();

    @Test
    public void 실행어라운드패턴_기존테스트() throws IOException {
        assertThat(executeAround.processFile()).isEqualTo(
                executeAround.processFileWithRamda((BufferedReader br) -> br.readLine())
        );
    }
}
