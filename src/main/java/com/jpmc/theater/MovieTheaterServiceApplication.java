package com.jpmc.theater;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Slf4j
public class MovieTheaterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieTheaterServiceApplication.class, args);
    }

}
