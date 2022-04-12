package com.appiciel.customerservice;

import com.appiciel.customerservice.dto.CustomerRequestDTO;
import com.appiciel.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start (CustomerService customerService){
        return args -> {
            customerService.save(new CustomerRequestDTO("C01","Amelie","amelie@email.com"));
            customerService.save(new CustomerRequestDTO("C02","John","john@email.com"));
            customerService.save(new CustomerRequestDTO("C03","Bader","bader@email.com"));
        };
    }
}
