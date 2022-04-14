package com.appiciel.billingservice;

import com.appiciel.billingservice.dto.BillRequestDTO;
import com.appiciel.billingservice.service.BillService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillService billService) {
        return args -> {
            billService.save(new BillRequestDTO(BigDecimal.valueOf(10000.0),"C01"));
            billService.save(new BillRequestDTO(BigDecimal.valueOf(50000.0),"C01"));
            billService.save(new BillRequestDTO(BigDecimal.valueOf(470000.0),"C02"));
        };
    }

}
