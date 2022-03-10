package com.inn.banking.dao;

import com.inn.banking.POJO.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

    @Transactional
    @Modifying
    Integer confirmAppointment(@Param("status") boolean status,
                               @Param("appointmentId") Integer appointmentId);

}
