package com.appiciel.customerservice.service;

import com.appiciel.customerservice.domain.Customer;
import com.appiciel.customerservice.dto.CustomerRequestDTO;
import com.appiciel.customerservice.dto.CustomerResponseDTO;
import com.appiciel.customerservice.mapper.CustomerMapper;
import com.appiciel.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        return customerMapper.customerToCustomerResponseDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDTO get(String id) {
        return customerMapper.customerToCustomerResponseDTO(customerRepository.getById(id));
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        return customerMapper.customerToCustomerResponseDTO(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> customerMapper.customerToCustomerResponseDTO(customer))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}
