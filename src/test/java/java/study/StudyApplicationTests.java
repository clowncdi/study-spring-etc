package java.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.*;

@SpringBootTest
class StudyApplicationTests {

    @Test
    void contextLoads() {
        then(1).isEqualTo(1);
    }

}
