package com.example.pet_care;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Dog sampleDog() {
        return new Dog(1, "Diamond", "Rushi", 6, "Labrador");
    }

    @Bean
    public Cat sampleCat() {
        return new Cat(2, "Mittens", "Alice", 2, true);
    }
}

