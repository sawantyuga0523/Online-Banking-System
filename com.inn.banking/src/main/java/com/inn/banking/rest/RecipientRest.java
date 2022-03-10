package com.inn.banking.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/recipient")
public interface RecipientRest {

    @PostMapping(path = "/addRecipient")
    ResponseEntity<?> addRecipient(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/getRecipientByUserId/{userId}")
    ResponseEntity<?> getRecipientByUserId(@PathVariable Integer userId);

    @PostMapping(path = "/updateRecipient")
    ResponseEntity<?> updateRecipient(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/deleteRecipient/{recipientId}")
    ResponseEntity<?> deleteRecipient(@PathVariable Integer recipientId);

}
