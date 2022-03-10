package com.inn.banking.Controller;

import com.inn.banking.rest.RecipientRest;
import com.inn.banking.serviceInterface.RecipientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RecipientController implements RecipientRest {

    @Autowired
    RecipientServiceInterface recipientServiceInterface;

    @Override
    public ResponseEntity<?> addRecipient(Map<String, String> requestMap) {
        try {
            return recipientServiceInterface.addRecipient(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getRecipientByUserId(Integer userId) {
        try {
            return recipientServiceInterface.getRecipientByUserId(userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateRecipient(Map<String, String> requestMap) {
        try {
            return recipientServiceInterface.updateRecipient(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> deleteRecipient(Integer recipientId) {
        try {
            return recipientServiceInterface.deleteRecipient(recipientId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
