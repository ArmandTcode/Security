package tb.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"tb.spring.controller"})
public class TemplarApplication {
	public static void main(String[] args) {
		SpringApplication.run(TemplarApplication.class, args);
	}

}
