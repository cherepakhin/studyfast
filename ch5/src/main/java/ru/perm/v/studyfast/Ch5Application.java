package ru.perm.v.studyfast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Ch5Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch5Application.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "droid")
	Droid createDroid() {
		Droid droid = new Droid();
		// Останется , если не переопределено в application.properties
		droid.setName("droidName");
		return droid;
	}
}
