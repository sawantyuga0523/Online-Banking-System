package com.inn.banking.serviceInterface;

import org.springframework.http.ResponseEntity;

public interface PrimaryTransactionServiceInterface {

    ResponseEntity<?> getPrimaryTransactionByAccountNo(String accountNumber);

}
