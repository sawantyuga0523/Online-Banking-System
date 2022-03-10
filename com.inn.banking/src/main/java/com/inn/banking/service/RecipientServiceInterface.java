package com.inn.banking.service;

import com.inn.banking.POJO.AppUser;
import com.inn.banking.POJO.Recipient;
import com.inn.banking.dao.RecipientDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class RecipientServiceInterface implements com.inn.banking.serviceInterface.RecipientServiceInterface {

    private Logger log = LogManager.getLogger(RecipientServiceInterface.class);

    @Autowired
    RecipientDao recipientDao;

    @Override
    public ResponseEntity<?> addRecipient(Map<String, String> requestMap) {
        try {
            if (validateRequestMap(requestMap)) {
                AppUser appUser = new AppUser();
                appUser.setId(Integer.parseInt(requestMap.get("appuserId")));

                Recipient recipient = recipientDao.findByaccountNumberAndUserId(requestMap.get("accountNumber"), appUser.getId());
                if (Objects.isNull(recipient)) {
                    log.info("I am in {}", recipient);
                    Recipient recipient1 = new Recipient();
                    recipient1.setAppUser(appUser);
                    recipient1.setName(requestMap.get("name"));
                    recipient1.setEmail(requestMap.get("email"));
                    recipient1.setPhone(requestMap.get("phone"));
                    recipient1.setAccountNumber(requestMap.get("accountNumber"));
                    recipient1.setDescription(requestMap.get("description"));
                    recipientDao.save(recipient1);
                    return new ResponseEntity<>("{\"message\":\"Recipient added.\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"Account already added.\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"Improper data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateRequestMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("phone") &&
                requestMap.containsKey("accountNumber") &&
                requestMap.containsKey("description") &&
                requestMap.containsKey("appuserId");
    }

    @Override
    public ResponseEntity<?> getRecipientByUserId(Integer userId) {
        try {
            return new ResponseEntity<>(recipientDao.getRecipientByUserId(userId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateRecipient(Map<String, String> requestMap) {
        try {
            if (validateUpdateRecipientMap(requestMap)) {
                Integer updateCount = recipientDao.updateRecipient(
                        requestMap.get("name"),
                        requestMap.get("email"),
                        requestMap.get("phone"),
                        requestMap.get("description"),
                        Integer.parseInt(requestMap.get("recipientId"))
                );
                return new ResponseEntity<>("{\"message\":\"Recipient Updated.\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"message\":\"Improper data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateUpdateRecipientMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("phone") &&
                requestMap.containsKey("description") &&
                requestMap.containsKey("recipientId");
    }

    @Override
    public ResponseEntity<?> deleteRecipient(Integer recipientId) {
        try {
            recipientDao.deleteById(recipientId);
            return new ResponseEntity<>("{\"message\":\"Recipient Deleted Successfully.\"}", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
