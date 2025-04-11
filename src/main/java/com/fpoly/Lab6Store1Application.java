package com.fpoly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fpoly.config.DotenvConfig;

@SpringBootApplication
public class Lab6Store1Application {

	public static void main(String[] args) {
		 new DotenvConfig();
		SpringApplication.run(Lab6Store1Application.class, args);
	}

}
