package com.example.videoServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VideoServerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {



		SpringApplication.run(VideoServerApplication.class, args);


	}


}
