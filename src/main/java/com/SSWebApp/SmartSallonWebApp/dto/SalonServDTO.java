package com.SSWebApp.SmartSallonWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalonServDTO {

    private Long id;

    @NotNull(message = "Gender is required")
    private String gender;

    @NotNull(message = "Service is required")
    private String serviceOffered;

    @NotNull(message = "Price is required")
    private String price;

}
