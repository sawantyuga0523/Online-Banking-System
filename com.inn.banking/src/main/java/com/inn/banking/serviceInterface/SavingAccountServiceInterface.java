package com.inn.banking.serviceInterface;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SavingAccountServiceInterface {

    ResponseEntity<?> processFund(Map<String, String> requestMap);

    ResponseEntity<?> transferFromSavingToAnother(Map<String, String> requestMap);

}
