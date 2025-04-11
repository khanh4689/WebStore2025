package com.fpoly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fpoly.config.DotenvConfig;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Lab6Store1Application {

	public static void main(String[] args) {
		 Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
	        dotenv.entries().forEach(entry ->
	            System.setProperty(entry.getKey(), entry.getValue())
	        );
		SpringApplication.run(Lab6Store1Application.class, args);
	}

}
