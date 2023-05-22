package com.SSWebApp.SmartSallonWebApp.models;

import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sallonservices")
public class SalonServ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Service is required")
    private String serviceOffered;

    @NotBlank(message = "Price is required")
    private String price;

    @OneToMany(mappedBy = "salonServ", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    // constructors, getters, and setters


}
