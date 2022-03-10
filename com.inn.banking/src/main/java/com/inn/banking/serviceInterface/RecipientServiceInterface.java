package com.inn.banking.serviceInterface;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RecipientServiceInterface {

    ResponseEntity<?> addRecipient(Map<String, String> requestMap);

    ResponseEntity<?> getRecipientByUserId(Integer userId);

    ResponseEntity<?> updateRecipient(Map<String, String> requestMap);

    ResponseEntity<?> deleteRecipient(Integer recipientId);

}
