package hu.sol.java2survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MikJava2SurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MikJava2SurveyApplication.class, args);
	}
}
