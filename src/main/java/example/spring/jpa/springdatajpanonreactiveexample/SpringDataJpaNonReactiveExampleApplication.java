package example.spring.jpa.springdatajpanonreactiveexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringDataJpaNonReactiveExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaNonReactiveExampleApplication.class, args);
	}

}
