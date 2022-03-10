package com.inn.banking.service;

import com.inn.banking.POJO.AppUser;
import com.inn.banking.POJO.Appointment;
import com.inn.banking.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class AppointmentServiceInterface implements com.inn.banking.serviceInterface.AppointmentServiceInterface {

    @Autowired
    AppointmentDao appointmentDao;

    @Override
    public ResponseEntity<?> scheduleAppointment(Map<String, Object> requestMap) {
        try {
            if (validateRequestMap(requestMap)) {
                AppUser appUser = new AppUser();
                appUser.setId(Integer.parseInt(requestMap.get("appuserId").toString()));

                Appointment appointment = new Appointment();
                appointment.setUser(appUser);
                Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(requestMap.get("date").toString());
                appointment.setDate(date1);
                appointment.setLocation(requestMap.get("location").toString());
                appointment.setDescription(requestMap.get("description").toString());

                appointmentDao.save(appointment);
                return new ResponseEntity<>("{\"message\":\"Appointment Scheduled.\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"message\":\"Improper data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("appuserId") &&
                requestMap.containsKey("date") &&
                requestMap.containsKey("location") &&
                requestMap.containsKey("description");
    }

    @Override
    public ResponseEntity<?> getAllAppointment() {
        try {
            return new ResponseEntity<>(appointmentDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> confirmAppointment(String status, Integer appointmentId) {
        try {
            appointmentDao.confirmAppointment(Boolean.parseBoolean(status), appointmentId);
            String message = "";
            if (Boolean.parseBoolean(status)) {
                message = "{\"message\":\"Appointment Confirmed.\"}";
            } else {
                message = "{\"message\":\"Appointment Cancelled.\"}";
            }
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
