package com.SSWebApp.SmartSallonWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CalendarDTO {
    private Long id;

    @NotNull(message = "Date and time is required")
    private LocalDateTime date;

    // constructors, getters, and setters

}
