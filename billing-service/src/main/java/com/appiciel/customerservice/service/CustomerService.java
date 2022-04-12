package com.appiciel.customerservice.service;

import com.appiciel.customerservice.domain.Customer;
import com.appiciel.customerservice.dto.CustomerRequestDTO;
import com.appiciel.customerservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO get(String id);
    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
    List<CustomerResponseDTO> listCustomers();

    void delete(Customer customer);
}
