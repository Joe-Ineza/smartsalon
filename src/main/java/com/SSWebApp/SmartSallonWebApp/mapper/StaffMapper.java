package com.SSWebApp.SmartSallonWebApp.mapper;

import com.SSWebApp.SmartSallonWebApp.dto.StaffDTO;
import com.SSWebApp.SmartSallonWebApp.models.Staff;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class StaffMapper {

    public static StaffDTO toDto(Staff staff) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setId(staff.getId());
        staffDTO.setName(staff.getName());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setPhoneNumber(staff.getPhoneNumber());
        return staffDTO;
    }

    public static Staff toEntity(StaffDTO staffDTO) {
        Staff staff = new Staff();
        staff.setId(staffDTO.getId());
        staff.setName(staffDTO.getName());
        staff.setEmail(staffDTO.getEmail());
        staff.setPhoneNumber(staffDTO.getPhoneNumber());
        return staff;
    }
}
