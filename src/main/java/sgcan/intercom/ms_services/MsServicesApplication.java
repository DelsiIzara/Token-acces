package sgcan.intercom.ms_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class MsServicesApplication {

	public static void main(String[] args) {

		SpringApplication.run(MsServicesApplication.class, args);

	}

}
