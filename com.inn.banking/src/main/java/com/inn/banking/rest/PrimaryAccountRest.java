package com.inn.banking.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/primaryAccount")
public interface PrimaryAccountRest {

    @PostMapping(path = "/processFund")
    ResponseEntity<?> processFund(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/transferBetweenAccount")
    ResponseEntity<?> transferBetweenAccount(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/transferFromPrimaryToAnother")
    ResponseEntity<?> transferFromPrimaryToAnother(@RequestBody Map<String, String> requestMap);

}
