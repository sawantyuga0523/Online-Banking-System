package com.inn.banking.serviceInterface;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AppUserServiceInterface {

    ResponseEntity<?> signUp(Map<String, String> requestMap);

    ResponseEntity<?> getUserProfile(Integer userId);

    ResponseEntity<?> updateProfile(Map<String, String> requestMap);

    ResponseEntity<?> login(Map<String, String> requestMap);

    ResponseEntity<?> getAllUserProfile();

    ResponseEntity<?> enableDisableUser(String status, Integer userId);
}
