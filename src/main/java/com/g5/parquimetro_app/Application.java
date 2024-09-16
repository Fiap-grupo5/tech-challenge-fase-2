package com.g5.parquimetro_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@GetMapping(path = "/")
	public String check(){
		return "<h1>Hello World</h1>";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
