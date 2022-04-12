package com.appiciel.customerservice.mapper;

import com.appiciel.customerservice.dto.CustomerRequestDTO;
import com.appiciel.customerservice.dto.CustomerResponseDTO;
import com.appiciel.customerservice.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
//    @Mapping(source="sourname",target = "targetName")
    CustomerRequestDTO customerRequestToCustomerDTO(Customer customer);
    Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerResponseDTOToCustomer(CustomerResponseDTO customerReponseDTO);
}
