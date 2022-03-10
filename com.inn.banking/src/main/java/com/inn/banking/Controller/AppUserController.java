package com.inn.banking.Controller;

import com.inn.banking.rest.AppUserRest;
import com.inn.banking.serviceInterface.AppUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AppUserController implements AppUserRest {

    @Autowired
    AppUserServiceInterface appUserServiceInterface;

    @Override
    public ResponseEntity<?> signUp(Map<String, String> requestMap) {
        try {
            return appUserServiceInterface.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getUserProfile(Integer userId) {
        try {
            return appUserServiceInterface.getUserProfile(userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateProfile(Map<String, String> requestMap) {
        try {
            return appUserServiceInterface.updateProfile(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> login(Map<String, String> requestMap) {
        try {
            return appUserServiceInterface.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllUserProfile() {
        try {
            return appUserServiceInterface.getAllUserProfile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> enableDisableUser(String status, Integer userId) {
        try {
            return appUserServiceInterface.enableDisableUser(status, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
