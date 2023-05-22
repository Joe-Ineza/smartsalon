package com.SSWebApp.SmartSallonWebApp.repository;

import com.SSWebApp.SmartSallonWebApp.models.Appointment;
import com.SSWebApp.SmartSallonWebApp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByEmail(String email);

//    Set<Appointment> findAppointmentsByCustomer(Long customerId);


}
