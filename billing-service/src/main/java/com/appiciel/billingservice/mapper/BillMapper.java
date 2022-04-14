package com.appiciel.billingservice.mapper;

import com.appiciel.billingservice.domain.Bill;
import com.appiciel.billingservice.dto.BillRequestDTO;
import com.appiciel.billingservice.dto.BillResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill fromBillDTO(BillRequestDTO billRequestDTO);
    BillResponseDTO fromBill(Bill bill);

}
