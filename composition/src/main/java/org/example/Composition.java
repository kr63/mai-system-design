package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(CompositionProperties.class)
@SpringBootApplication
public class Composition {

    public static void main(String[] args) {
        SpringApplication.run(Composition.class, args);
    }

}
