package com.appiciel.billingservice.dto;

import com.appiciel.billingservice.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class BillRequestDTO {
    private BigDecimal amount;
    private String customerId;
}
