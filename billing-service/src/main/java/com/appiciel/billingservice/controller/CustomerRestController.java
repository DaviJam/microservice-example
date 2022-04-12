package com.appiciel.billingservice.controller;

import com.appiciel.billingservice.dto.CustomerRequestDTO;
import com.appiciel.billingservice.dto.CustomerResponseDTO;
import com.appiciel.billingservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class CustomerRestController {

    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customers")
    public List<CustomerResponseDTO> allCustomers(){
        return customerService.listCustomers();
    }

    @PostMapping(path = "/customers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDTO);
    }

    @GetMapping(path= "/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id) {
        return customerService.get(id);
    }
}
