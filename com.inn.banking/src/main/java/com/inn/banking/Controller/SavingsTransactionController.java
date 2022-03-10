package com.inn.banking.Controller;

import com.inn.banking.rest.SavingsTransactionRest;
import com.inn.banking.serviceInterface.SavingsTransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingsTransactionController implements SavingsTransactionRest {

    @Autowired
    SavingsTransactionServiceInterface savingsTransactionServiceInterface;

    @Override
    public ResponseEntity<?> getSavingTransactionByAccountNo(String accountNumber) {
        try {
            return savingsTransactionServiceInterface.getSavingTransactionByAccountNo(accountNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
