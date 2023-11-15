package pdr.chorumeblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ChorumeBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChorumeBlogApplication.class, args);
    }


}
