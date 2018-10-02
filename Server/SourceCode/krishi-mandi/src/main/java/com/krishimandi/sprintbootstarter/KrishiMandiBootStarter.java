package com.krishimandi.sprintbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.krishimandi.application.ConfigureSystem;

/**
 * This is the entry point for the application.
 *
 */
@ComponentScan("com.krishimandi")
@SpringBootApplication
public class KrishiMandiBootStarter {

	public static void main(String[] args) {

		try {
		
			ConfigureSystem configurationInstance = new ConfigureSystem();
			configurationInstance.prepareDumpingGround();
			configurationInstance.mapLocalizationContent();
			
			/* Start the application on Spring boot */
			SpringApplication.run(KrishiMandiBootStarter.class, args); 
		}catch (Exception e) {

			System.out.println("Exception Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
