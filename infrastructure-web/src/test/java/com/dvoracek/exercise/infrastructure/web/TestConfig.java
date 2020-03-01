package  com.dvoracek.exercise.infrastructure.web;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.dvoracek.exercise")
@EntityScan(basePackages = "com.dvoracek.exercise.domain")
@EnableJpaRepositories(basePackages = "com.dvoracek.exercise.domain")
public class TestConfig {
}