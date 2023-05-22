package com.SSWebApp.SmartSallonWebApp.mapper;

import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.models.Customer;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class CustomerMapper {

    public static CustomerDTO toDto(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        return dto;
    }

    public static Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        return customer;
    }
}
