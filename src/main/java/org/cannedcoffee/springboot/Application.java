package org.cannedcoffee.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//enables auditing used in BaseTimeEntity class
//JpaAuditing must be removed from here because it's scanned from test controller using @WebMvcTest,
// which does not scan @Repository or @Component, meaning no entity is standing.
// @EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
