package tr.com.khg.personneldetailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PersonnelDetailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnelDetailServiceApplication.class, args);
	}

}
