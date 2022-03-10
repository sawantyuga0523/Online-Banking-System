package com.inn.banking.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/savingAccount")
public interface SavingAccountRest {

    @PostMapping(path = "/processFund")
    ResponseEntity<?> processFund(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/transferFromSavingToAnother")
    ResponseEntity<?> transferFromSavingToAnother(@RequestBody Map<String, String> requestMap);

}
