package com.SSWebApp.SmartSallonWebApp.dto;


import com.SSWebApp.SmartSallonWebApp.models.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Long id;

    @NotBlank(message = "Message is required")
    private String message;

    @NotNull(message = "Sent date and time is required")
    private LocalDateTime sentDateTime;

    @NotNull(message = "Recipient is required")
    private StaffDTO recipient;

    // constructors, getters, and setters
}
