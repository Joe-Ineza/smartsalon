package com.SSWebApp.SmartSallonWebApp.service.impl;

import com.SSWebApp.SmartSallonWebApp.Exceptions.ResourceNotFoundException;
import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;

import com.SSWebApp.SmartSallonWebApp.mapper.CustomerMapper;
import com.SSWebApp.SmartSallonWebApp.models.Appointment;
import com.SSWebApp.SmartSallonWebApp.models.Customer;
import com.SSWebApp.SmartSallonWebApp.repository.CustomerRepository;
import com.SSWebApp.SmartSallonWebApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer","id", id));
        Customer updatedCustomer = customerMapper.toEntity(customerDTO);
        updatedCustomer.setId(existingCustomer.getId());
        Customer savedCustomer = customerRepository.save(updatedCustomer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getAllCustomersByEmail(String email) {
        List<Customer> customers = customerRepository.findAllByEmail(email);
        return customers.stream().map(CustomerMapper::toDto).collect(Collectors.toList());

    }

//    public Set<Appointment> getAllAppointmentByCustomer(Long customerId) {
//        Set<Appointment> appoints = customerRepository.findAppointmentsByCustomer(customerId);
//        return appoints;
//
//    }
}
