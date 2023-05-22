package com.SSWebApp.SmartSallonWebApp.models;
import javax.persistence.*;
import lombok.*;
import java.util.*;


import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    // constructors, getters, and setters

    public Staff(Long id) {
        this.id = id;
    }

}
