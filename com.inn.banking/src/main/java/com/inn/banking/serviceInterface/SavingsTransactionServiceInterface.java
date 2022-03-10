package com.inn.banking.serviceInterface;

import org.springframework.http.ResponseEntity;

public interface SavingsTransactionServiceInterface {

    ResponseEntity<?> getSavingTransactionByAccountNo(String accountNumber);

}
