package com.studentapi;

import com.studentapi.model.Student;
import com.studentapi.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }

    /** Loads a few sample students so GET calls return data straight away. */
    @Bean
    CommandLineRunner seedData(StudentRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Student(null, "Arjun Rao", "arjun@example.com", 20, "Computer Science", 88.5));
                repo.save(new Student(null, "Meera Nair", "meera@example.com", 21, "Information Tech", 92.0));
                repo.save(new Student(null, "Rahul Verma", "rahul@example.com", 19, "Electronics", 67.25));
            }
        };
    }
}
