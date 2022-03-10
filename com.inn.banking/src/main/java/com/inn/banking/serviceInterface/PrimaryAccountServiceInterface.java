package com.inn.banking.serviceInterface;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PrimaryAccountServiceInterface {

    ResponseEntity<?> processFund(Map<String, String> requestMap);

    ResponseEntity<?> transferBetweenAccount(Map<String, String> requestMap);

    ResponseEntity<?> transferFromPrimaryToAnother(Map<String, String> requestMap);

}
