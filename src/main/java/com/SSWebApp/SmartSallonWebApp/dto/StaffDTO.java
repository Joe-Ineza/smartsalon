package com.SSWebApp.SmartSallonWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    public StaffDTO(Long id) {
        this.id = id;
    }


    // constructors, getters, and setters
}
