package com.inmaytide.sm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebApplication {

    @RequestMapping("/hello")
    public String hello() {
        return "hello, world";
    }

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
