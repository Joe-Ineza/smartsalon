package com.SSWebApp.SmartSallonWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    public CustomerDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // constructors, getters, and setters
}
