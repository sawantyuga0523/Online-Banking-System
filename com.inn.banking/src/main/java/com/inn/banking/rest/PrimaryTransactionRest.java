package com.inn.banking.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/primaryTransaction")
public interface PrimaryTransactionRest {

    @GetMapping(path = "/getPrimaryTransactionByAccountNo/{accountNumber}")
    ResponseEntity<?> getPrimaryTransactionByAccountNo(@PathVariable String accountNumber);

}
