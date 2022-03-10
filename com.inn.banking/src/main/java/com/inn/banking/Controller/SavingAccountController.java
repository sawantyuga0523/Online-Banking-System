package com.inn.banking.Controller;

import com.inn.banking.rest.SavingAccountRest;
import com.inn.banking.serviceInterface.SavingAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SavingAccountController implements SavingAccountRest {

    @Autowired
    SavingAccountServiceInterface savingAccountServiceInterface;

    @Override
    public ResponseEntity<?> processFund(Map<String, String> requestMap) {
        try {
            return savingAccountServiceInterface.processFund(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> transferFromSavingToAnother(Map<String, String> requestMap) {
        try {
            return savingAccountServiceInterface.transferFromSavingToAnother(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
