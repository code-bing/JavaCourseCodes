package com.github.yibing.springboot01;

import example.springboot.starter.service.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot01Application implements CommandLineRunner {

    @Autowired
    private Student student;
    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        student.init();
        System.out.println(student.getId());
    }
}
