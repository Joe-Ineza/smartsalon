package com.SSWebApp.SmartSallonWebApp.dto;

import com.SSWebApp.SmartSallonWebApp.models.Customer;
import com.SSWebApp.SmartSallonWebApp.models.SalonServ;
import com.SSWebApp.SmartSallonWebApp.models.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;

    @NotNull(message = "Appointment Time is required")
    private String dateTime;


    @NotNull(message = "Customer is required")
    private Customer customer;

    @NotNull(message = "Staff is required")
    private Staff staff;

    @NotNull(message = "Staff is required")
    private SalonServ salonServ;






    // getters and setters for other fields
}
