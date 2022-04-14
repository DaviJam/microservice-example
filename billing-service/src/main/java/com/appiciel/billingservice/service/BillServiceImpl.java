package com.appiciel.billingservice.service;

import com.appiciel.billingservice.domain.Bill;
import com.appiciel.billingservice.domain.Customer;
import com.appiciel.billingservice.dto.BillRequestDTO;
import com.appiciel.billingservice.dto.BillResponseDTO;
import com.appiciel.billingservice.exception.CustomerNotFoundException;
import com.appiciel.billingservice.mapper.BillMapper;
import com.appiciel.billingservice.repository.BillRepository;
import com.appiciel.billingservice.restclient.CustomerServiceRestClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    private BillRepository billRepository;
    private BillMapper billMapper;
    private CustomerServiceRestClient customerServiceRestClient;

    public BillServiceImpl(BillRepository billRepository, BillMapper billMapper, CustomerServiceRestClient customerServiceRestClient) {
        this.billRepository = billRepository;
        this.billMapper = billMapper;
        this.customerServiceRestClient = customerServiceRestClient;
    }

    @Override
    public BillResponseDTO save(BillRequestDTO billRequestDTO) throws CustomerNotFoundException {
        Customer customer = null;
        try {
            customer = customerServiceRestClient.customerById(billRequestDTO.getCustomerId());
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer does not exists");
        }

        Bill bill = billMapper.fromBillDTO(billRequestDTO);
        bill.setId(UUID.randomUUID().toString());
        bill.setDate(new Date());

        Bill savedBill = billRepository.save(bill);
        savedBill.setCustomer(customer);
        return billMapper.fromBill(savedBill);
    }

    @Override
    public BillResponseDTO get(String billId) {
        Bill bill = billRepository.getById(billId);
        Customer customer = customerServiceRestClient.customerById(bill.getCustomerId());
        bill.setCustomer(customer);
        return billMapper.fromBill(bill);
    }

    @Override
    public List<BillResponseDTO> billsByCustomerId(String customerId) {
        return billRepository
                .findByCustomerId(customerId)
                    .stream()
                        .map(bill -> {
                            bill.setCustomer(customerServiceRestClient.customerById(bill.getCustomerId()));
                            return billMapper.fromBill(bill);
                        })
                        .collect(Collectors.toList());
    }

    @Override
    public List<BillResponseDTO> getAll() {
        return billRepository.findAll().stream().map(bill -> {
            bill.setCustomer(customerServiceRestClient.customerById(bill.getCustomerId()));
        return billMapper.fromBill(bill);
        }).collect(Collectors.toList());
    }
}
