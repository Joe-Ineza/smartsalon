package com.SSWebApp.SmartSallonWebApp.controller;

import com.SSWebApp.SmartSallonWebApp.dto.AppointmentDTO;
import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.service.AppointmentService;

import com.SSWebApp.SmartSallonWebApp.service.SalonServService;
import com.SSWebApp.SmartSallonWebApp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.SSWebApp.SmartSallonWebApp.service.CustomerService;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final CustomerService customerService;

    private final StaffService staffService;

    private final SalonServService salonServService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, CustomerService customerService, StaffService staffService, SalonServService salonServService) {
        this.appointmentService = appointmentService;
        this.customerService = customerService;
        this.staffService = staffService;
        this.salonServService = salonServService;
    }


    @GetMapping("/appointment/create")
    public String showCreateAppointmentForm(Model model) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        model.addAttribute("appointmentDTO", appointmentDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<CustomerDTO> customers = customerService.getAllCustomersByEmail(username);
        model.addAttribute("customers",customers);
        model.addAttribute("staffs", staffService.findAll());
        model.addAttribute("servicesOffered", salonServService.getAllSalonServ());
        return "create_appointment";
    }

    @PostMapping("/appointment/create")
    public String createAppointment(@ModelAttribute("appointmentDTO") @Valid AppointmentDTO appointmentDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "create_appointment";
        }
        appointmentService.createAppointment(appointmentDTO);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments")
    public String getAppointments(Model model) {
        List<AppointmentDTO> appointments = appointmentService.getAppointments();
        model.addAttribute("appointments", appointments);
        return "appointment_List";
    }

    @GetMapping("appointment/{id}")
    public String getAppointmentById(@PathVariable Long id, Model model) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointment_List";
    }

    @PostMapping("appointment/{id}")
    public String updateAppointment(@PathVariable Long id, @ModelAttribute("appointmentDTO") @Valid AppointmentDTO appointmentDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "appointment_List";
        }
        if (!appointmentDTO.getId().equals(id)) {
            throw new IllegalArgumentException("Mismatched appointment Ids");
        }
        appointmentService.updateAppointment(appointmentDTO);
        return "redirect:/appointments";
    }



    @GetMapping("appointment/{id}/edit")
    public String showUpdateAppointmentForm(@PathVariable Long id, Model model) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointmentDTO", appointment);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("staffs", staffService.findAll());
        model.addAttribute("servicesOffered", salonServService.getAllSalonServ());

        return "edit_appointment";
    }


    @PostMapping("appointment/{id}/delete")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }

    @GetMapping("appointment/date")
    public String getAppointmentsByDate(@RequestParam("date") String dateString, Model model) {
        LocalDate date = LocalDate.parse(dateString);
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByDate(date);
        model.addAttribute("appointments", appointments);
        return "appointments_by_date";
    }
}
