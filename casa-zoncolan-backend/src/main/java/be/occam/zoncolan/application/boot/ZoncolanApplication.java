package be.occam.zoncolan.application.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.occam.zoncolan.application.config.CasaZoncolanApplicationConfig;

@SpringBootApplication(scanBasePackages="be.occam.zoncolan")
public class ZoncolanApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ZoncolanApplication.class);
	}
	
}
