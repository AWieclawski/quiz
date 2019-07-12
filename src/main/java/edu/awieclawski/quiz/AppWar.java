package edu.awieclawski.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Enables self executing war package 
@SpringBootApplication
public class AppWar {

    public static void main(String[] args) {
        SpringApplication.run(AppWar.class, args);
    }

}
