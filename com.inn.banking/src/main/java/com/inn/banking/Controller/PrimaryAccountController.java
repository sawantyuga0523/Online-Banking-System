package com.inn.banking.Controller;

import com.inn.banking.rest.PrimaryAccountRest;
import com.inn.banking.serviceInterface.PrimaryAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PrimaryAccountController implements PrimaryAccountRest {

    @Autowired
    PrimaryAccountServiceInterface primaryAccountServiceInterface;

    @Override
    public ResponseEntity<?> processFund(Map<String, String> requestMap) {
        try {
            return primaryAccountServiceInterface.processFund(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<?> transferBetweenAccount(Map<String, String> requestMap) {
        try {
            return primaryAccountServiceInterface.transferBetweenAccount(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<?> transferFromPrimaryToAnother(Map<String, String> requestMap) {
        try {
            return primaryAccountServiceInterface.transferFromPrimaryToAnother(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
