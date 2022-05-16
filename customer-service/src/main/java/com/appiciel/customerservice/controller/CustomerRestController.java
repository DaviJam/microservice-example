package com.appiciel.customerservice.controller;

import com.appiciel.customerservice.dto.CustomerRequestDTO;
import com.appiciel.customerservice.dto.CustomerResponseDTO;
import com.appiciel.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
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
        return customerService.save(customerRequestDTO);
    }

    @GetMapping(path= "/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id) {
        return customerService.get(id);
    }
}
