package com.SSWebApp.SmartSallonWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private Long id;
    @NotBlank(message = "userName is required")
    private String userName;
    @NotBlank(message = "password")
    private String password;
}
