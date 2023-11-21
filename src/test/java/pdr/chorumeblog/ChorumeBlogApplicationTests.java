package pdr.chorumeblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ChorumeBlogApplication.class)
@ActiveProfiles("test")
class ChorumeBlogApplicationTests {

    @Test
    void contextLoads() {
    }

}
