package com.appiciel.billingservice.dto;

import com.appiciel.billingservice.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class BillResponseDTO {
    private String id;
    private Date date;
    private BigDecimal amount;
    private Customer customer;
}
