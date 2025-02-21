package com.example.demo;

import com.example.demo.entities.Department;
import com.example.demo.repositories.DepRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    CommandLineRunner initDatabase(DepRepository depRepository) {
//        return args -> {
//            if (depRepository.count() == 0) {
//                List<Department> departments = List.of(
//                        new Department("IT Department"),
//                        new Department("HR Department"),
//                        new Department("Finance Department")
//                );
//                depRepository.saveAll(departments);
//                System.out.println("Départements initialisés avec succès !");
//            }
//        };
//    }

}
