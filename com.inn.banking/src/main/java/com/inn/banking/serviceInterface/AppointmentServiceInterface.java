package com.inn.banking.serviceInterface;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AppointmentServiceInterface {

    ResponseEntity<?> scheduleAppointment(Map<String, Object> requestMap);

    ResponseEntity<?> getAllAppointment();

    ResponseEntity<?> confirmAppointment(String status, Integer appointmentId);

}
