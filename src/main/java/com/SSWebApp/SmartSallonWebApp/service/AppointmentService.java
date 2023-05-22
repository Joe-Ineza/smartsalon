package com.SSWebApp.SmartSallonWebApp.service;

import com.SSWebApp.SmartSallonWebApp.dto.AppointmentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    List<AppointmentDTO> getAppointments();

    AppointmentDTO getAppointmentById(Long id);
    AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO);

    void deleteAppointment(Long id);
    List<AppointmentDTO> getAppointmentsByDate(LocalDate date);


    List<AppointmentDTO> findByDateTime(LocalDateTime dateTime);

    List<AppointmentDTO> findByDate(LocalDate date);
}
