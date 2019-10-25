package com.demoforimpel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.demoforimpel")
public class DemoForImpelApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoForImpelApplication.class, args);
	}

}
