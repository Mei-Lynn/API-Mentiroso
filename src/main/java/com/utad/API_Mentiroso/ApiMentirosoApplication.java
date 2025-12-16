package com.utad.API_Mentiroso;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiMentirosoApplication {
	UUID idSala = UUID.randomUUID();

	//String idStr = idSala.toString();

	public static void main(String[] args) {
		SpringApplication.run(ApiMentirosoApplication.class, args);
	}

}
