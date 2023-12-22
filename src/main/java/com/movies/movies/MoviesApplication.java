package com.movies.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@RestController
public class MoviesApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		String mongoDatabase = dotenv.get("MONGO_DATABASE");
		String mongoUri = dotenv.get("MONGO_URI");
		SpringApplication.run(MoviesApplication.class, args);
	}
	@GetMapping("/root")
	public String apiRoot(){
		return "Hello World!";
	}

}
