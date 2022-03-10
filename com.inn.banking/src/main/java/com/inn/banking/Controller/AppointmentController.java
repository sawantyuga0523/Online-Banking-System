package com.inn.banking.Controller;

import com.inn.banking.rest.AppointmentRest;
import com.inn.banking.serviceInterface.AppointmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AppointmentController implements AppointmentRest {

    @Autowired
    AppointmentServiceInterface appointmentServiceInterface;

    @Override
    public ResponseEntity<?> scheduleAppointment(Map<String, Object> requestMap) {
        try {
            return appointmentServiceInterface.scheduleAppointment(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllAppointment() {
        try {
            return appointmentServiceInterface.getAllAppointment();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> confirmAppointment(String status, Integer appointmentId) {
        try {
            return appointmentServiceInterface.confirmAppointment(status, appointmentId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
