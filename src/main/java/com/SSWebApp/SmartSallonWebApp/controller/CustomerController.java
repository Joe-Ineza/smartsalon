package com.SSWebApp.SmartSallonWebApp.controller;

import com.SSWebApp.SmartSallonWebApp.Exceptions.ResourceNotFoundException;
import com.SSWebApp.SmartSallonWebApp.dto.AppointmentDTO;
import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.mapper.CustomerMapper;
import com.SSWebApp.SmartSallonWebApp.models.Appointment;
import com.SSWebApp.SmartSallonWebApp.models.Customer;
import com.SSWebApp.SmartSallonWebApp.repository.CustomerRepository;
import com.SSWebApp.SmartSallonWebApp.service.AppointmentService;
import com.SSWebApp.SmartSallonWebApp.service.CustomerService;
import com.SSWebApp.SmartSallonWebApp.service.SalonServService;
import com.SSWebApp.SmartSallonWebApp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.stereotype.Controller;


@Controller
public class CustomerController {


    private final CustomerRepository customerRepository;
    private final AppointmentService appointmentService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    private final StaffService staffService;

    private final SalonServService salonServService;
    @Autowired
    public CustomerController(CustomerRepository customerRepository, AppointmentService appointmentService, CustomerService customerService, CustomerMapper customerMapper, StaffService staffService, SalonServService salonServService) {
        this.customerRepository = customerRepository;
        this.appointmentService = appointmentService;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.staffService = staffService;
        this.salonServService = salonServService;
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customer_List";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("customers/newC")
    public String showNewCustomerForm(Model model) {
        Customer customer = new Customer();

        model.addAttribute("customer", customer);
        return "new_customer";
    }

    @PostMapping("customers/newC")
    public String createCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "new_customer";
        }

        customerRepository.save(customer);

        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String showEditCustomerForm(@PathVariable(value = "id") Long customerId, Model model) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        model.addAttribute("customer", customer);
        return "edit_customer";
    }

    @PostMapping("/{id}/edit")
    public String updateCustomer(@PathVariable(value = "id") Long customerId, @Valid @ModelAttribute("customer") Customer customerDetails, BindingResult result) throws ResourceNotFoundException {
        if (result.hasErrors()) {
            return "edit_customer";
        }

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());

        final Customer updatedCustomer = customerRepository.save(customer);
        return "redirect:/customers";
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @GetMapping("/customerLandPage")
    public String customerPage(Model model)
    {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        model.addAttribute("appointmentDTO", appointmentDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<CustomerDTO> customers = customerService.getAllCustomersByEmail(username);
//        CustomerDTO customer = customerService.getCustomerById(customers.get(0).getId());
//        Customer customerChanged = customerMapper.toEntity(customer);
//        Set<Appointment> appoints = customerService.getAllAppointmentByCustomer(customerChanged.getId());
////        model.addAttribute("appointments", appointments);
////        Set<Appointment> appoints = customerService.getAllAppointmentByCustomer(customers.get(0).getId());
//        model.addAttribute("appoints",appoints);
        model.addAttribute("customers",customers);
        model.addAttribute("staffs", staffService.findAll());
        model.addAttribute("servicesOffered", salonServService.getAllSalonServ());

        return "customerLandPage";
    }
    @PostMapping("/appointment-create")
    public String createAppointment(@ModelAttribute("appointmentDTO") @Valid AppointmentDTO appointmentDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "create_appointment";
        }
        appointmentService.createAppointment(appointmentDTO);
        return "redirect:/customerLandPage";
    }

}
