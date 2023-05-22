package com.SSWebApp.SmartSallonWebApp.service.impl;

import com.SSWebApp.SmartSallonWebApp.dto.AppointmentDTO;
import com.SSWebApp.SmartSallonWebApp.service.AppointmentService;


import com.SSWebApp.SmartSallonWebApp.mapper.AppointmentMapper;
import com.SSWebApp.SmartSallonWebApp.models.Appointment;
import com.SSWebApp.SmartSallonWebApp.repository.AppointmentRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;



    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }


    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
        appointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public List<AppointmentDTO> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(AppointmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentRepository.findById(appointmentDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + appointmentDTO.getId()));
        appointmentMapper.updateModelFromDto(appointmentDTO, appointment);
        appointmentRepository.save(appointment);
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid appointment Id:" + id));
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDate(LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findAllByDateTimeBetween(date.atStartOfDay(), date.atTime(23, 59, 59));
        return appointments.stream().map(AppointmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findByDateTime(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public List<AppointmentDTO> findByDate(LocalDate date) {

        List<Appointment> appointments = appointmentRepository.findAllByDateTimeBetween(
                    date.atStartOfDay(),
                    date.atTime(23, 59, 59)
            );
            return appointments.stream().map(AppointmentMapper::toDto).collect(Collectors.toList());
    }
}
