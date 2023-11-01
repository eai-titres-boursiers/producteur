package fr.miage.producteur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories
public class ProducteurApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducteurApplication.class, args);
    }

}
