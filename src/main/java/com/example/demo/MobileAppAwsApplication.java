package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.ui.controller.DroneController;
import com.example.demo.ui.controller.MedicationController;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackageClasses = DroneController.class)
@ComponentScan(basePackageClasses = MedicationController.class)
@ComponentScan("com.example.demo.service")
 
public class MobileAppAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppAwsApplication.class, args);
	}
}
