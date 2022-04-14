package com.appiciel.billingservice.service;

import com.appiciel.billingservice.dto.BillRequestDTO;
import com.appiciel.billingservice.dto.BillResponseDTO;
import com.appiciel.billingservice.exception.CustomerNotFoundException;

import java.util.List;


public interface BillService {
    BillResponseDTO save(BillRequestDTO billRequestDTO) throws CustomerNotFoundException;
    BillResponseDTO get(String billId);
    List<BillResponseDTO> billsByCustomerId(String customerId);
    List<BillResponseDTO> getAll();
}
