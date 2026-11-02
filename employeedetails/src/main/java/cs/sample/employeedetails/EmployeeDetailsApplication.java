package cs.sample.employeedetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class EmployeeDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetailsApplication.class, args);
		//new SpringApplicationBuilder(EmployeeDetailsApplication.class).web(WebApplicationType.REACTIVE).run(args);
	}

}
