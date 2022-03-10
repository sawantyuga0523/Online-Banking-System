package com.inn.banking.service;

import com.inn.banking.dao.PrimaryTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimaryTransactionServiceInterface implements com.inn.banking.serviceInterface.PrimaryTransactionServiceInterface {

    @Autowired
    PrimaryTransactionDao primaryTransactionDao;

    @Override
    public ResponseEntity<?> getPrimaryTransactionByAccountNo(String accountNumber) {
        try {
            return new ResponseEntity<>(primaryTransactionDao.getPrimaryTransactionByAccountNo(Long.parseLong(accountNumber)), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
