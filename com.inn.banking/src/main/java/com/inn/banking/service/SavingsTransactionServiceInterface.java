package com.inn.banking.service;

import com.inn.banking.dao.SavingsTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SavingsTransactionServiceInterface implements com.inn.banking.serviceInterface.SavingsTransactionServiceInterface {

    @Autowired
    SavingsTransactionDao savingsTransactionDao;

    @Override
    public ResponseEntity<?> getSavingTransactionByAccountNo(String accountNumber) {
        try {
            return new ResponseEntity<>(savingsTransactionDao.getSavingTransactionByAccountNo(Long.parseLong(accountNumber)), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
