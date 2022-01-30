package gae.piaz.jwtblacklisting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("gae.piaz.jwtblacklisting")
@EnableCaching
public class JwtblacklistingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtblacklistingApplication.class, args);
    }

}
