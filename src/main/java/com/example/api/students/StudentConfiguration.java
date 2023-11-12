package com.example.api.students;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            studentRepository.saveAll(List.of(
                    new Student("Alex", "alex@mail.ru",LocalDate.of(2000, Month.AUGUST, 12)),
                    new Student("Sam", "sam@mail.ru", LocalDate.of(2002, Month.NOVEMBER, 1))
            ));
        };
    }

}
