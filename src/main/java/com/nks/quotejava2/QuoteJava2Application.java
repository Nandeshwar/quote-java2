package com.nks.quotejava2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class QuoteJava2Application {
    public static void main(String[] args) {
        SpringApplication.run(QuoteJava2Application.class, args);
    }
}
