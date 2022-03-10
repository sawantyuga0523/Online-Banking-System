package com.inn.banking.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/appuser")
public interface AppUserRest {

    @PostMapping(path = "/signUp")
    ResponseEntity<?> signUp(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/getUserProfile/{userId}")
    ResponseEntity<?> getUserProfile(@PathVariable(required = true) Integer userId);

    @PostMapping(path = "/updateProfile")
    ResponseEntity<?> updateProfile(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/login")
    ResponseEntity<?> login(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/getAllUserProfile")
    ResponseEntity<?> getAllUserProfile();

    @GetMapping(path = "/enableDisableUser/{status}/{userId}")
    ResponseEntity<?> enableDisableUser(@PathVariable(required = true) String status,
                                        @PathVariable(required = true) Integer userId);

}
