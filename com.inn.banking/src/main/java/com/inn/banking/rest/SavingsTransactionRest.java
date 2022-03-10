package com.inn.banking.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins = "*")
@RequestMapping(path = "/savingsTransaction")
public interface SavingsTransactionRest {

    @GetMapping(path = "/getSavingTransactionByAccountNo/{accountNumber}")
    ResponseEntity<?> getSavingTransactionByAccountNo(@PathVariable String accountNumber);

}
