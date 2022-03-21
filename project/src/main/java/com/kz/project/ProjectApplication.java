package com.kz.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        LocalDate loc = LocalDate.now();
        System.out.println(loc);
        SpringApplication.run(ProjectApplication.class, args);
    }

}
