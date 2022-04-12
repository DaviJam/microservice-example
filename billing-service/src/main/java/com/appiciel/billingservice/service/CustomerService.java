package com.appiciel.billingservice.service;

import com.appiciel.billingservice.domain.Customer;
import com.appiciel.billingservice.dto.CustomerRequestDTO;
import com.appiciel.billingservice.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO get(String id);
    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
    List<CustomerResponseDTO> listCustomers();

    void delete(Customer customer);
}
