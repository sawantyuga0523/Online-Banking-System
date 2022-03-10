package com.inn.banking.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/appointment")
public interface AppointmentRest {

    @PostMapping(path = "/scheduleAppointment")
    ResponseEntity<?> scheduleAppointment(@RequestBody Map<String, Object> requestMap);

    @GetMapping(path = "/getAllAppointment")
    ResponseEntity<?> getAllAppointment();

    @GetMapping(path = "/confirmAppointment/{status}/{appointmentId}")
    ResponseEntity<?> confirmAppointment(@PathVariable(required = true) String status,
                                         @PathVariable(required = true) Integer appointmentId);
}
