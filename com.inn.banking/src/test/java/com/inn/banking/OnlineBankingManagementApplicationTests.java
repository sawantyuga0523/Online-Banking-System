package com.inn.banking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


import com.inn.banking.dao.AppointmentDao;
import com.inn.banking.rest.AppUserRest;
import com.inn.banking.rest.AppointmentRest;


@SpringBootTest
class OnlineBankingManagementApplicationTests {

	@Autowired
	AppUserRest appUserRest;
	 AppointmentRest  appointmentRest;
	 AppointmentDao  appointmentDao;
	@Test
	void contexLoads() {
		
	}
	@Test
    void login() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("username", "yuga");
        requestMap.put("password", "yuga123");
        HttpStatus statusCode = appUserRest.login(requestMap).getStatusCode();
        Assertions.assertTrue(statusCode == HttpStatus.OK);
        
    }
	
	}
