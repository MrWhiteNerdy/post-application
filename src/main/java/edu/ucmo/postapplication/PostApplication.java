package edu.ucmo.postapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PostApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }

}
