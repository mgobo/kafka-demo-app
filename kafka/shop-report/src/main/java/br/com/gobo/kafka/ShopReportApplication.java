package br.com.gobo.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ShopReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopReportApplication.class, args);
    }

}
