package com.SSWebApp.SmartSallonWebApp.service;

import com.SSWebApp.SmartSallonWebApp.dto.AppointmentDTO;
import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.models.Appointment;
import com.SSWebApp.SmartSallonWebApp.models.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long id);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomerById(Long id);
    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> getAllCustomersByEmail(String email);

//    Set<Appointment> getAllAppointmentByCustomer(Long customerId);
}
