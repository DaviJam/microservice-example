package com.appiciel.billingservice.mapper;

import com.appiciel.billingservice.dto.CustomerRequestDTO;
import com.appiciel.billingservice.dto.CustomerResponseDTO;
import com.appiciel.billingservice.domain.Customer;
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
