package com.appiciel.billingservice.controller;

import com.appiciel.billingservice.domain.Bill;
import com.appiciel.billingservice.dto.BillRequestDTO;
import com.appiciel.billingservice.dto.BillResponseDTO;
import com.appiciel.billingservice.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/")
public class BillRestController {
    private BillService billService;

    public BillRestController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping(path="/bills/{id}")
    public BillResponseDTO getBill(@PathVariable String id){
        return billService.get(id);
    }

    @GetMapping(path="/bills")
    public List<BillResponseDTO> getBills(){
        return billService.getAll();
    }

    @GetMapping(path="/billsByCustomer/{customerId}")
    public List<BillResponseDTO> getBillByCustomer(@PathVariable String customerId){
        return billService.billsByCustomerId(customerId);
    }


    @PostMapping(path = "/bills")
    public BillResponseDTO save(@RequestBody BillRequestDTO requestDTO){
        return billService.save(requestDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
