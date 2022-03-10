package com.inn.banking.Controller;

import com.inn.banking.rest.PrimaryTransactionRest;
import com.inn.banking.serviceInterface.PrimaryTransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimaryTransactionController implements PrimaryTransactionRest {

    @Autowired
    PrimaryTransactionServiceInterface primaryTransactionServiceInterface;

    @Override
    public ResponseEntity<?> getPrimaryTransactionByAccountNo(String accountNumber) {
        try {
            return primaryTransactionServiceInterface.getPrimaryTransactionByAccountNo(accountNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
