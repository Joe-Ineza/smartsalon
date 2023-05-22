package com.SSWebApp.SmartSallonWebApp.mapper;

import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.dto.UsersDTO;
import com.SSWebApp.SmartSallonWebApp.models.Customer;
import com.SSWebApp.SmartSallonWebApp.models.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public static UsersDTO toDto(Users users) {
        UsersDTO dto = new UsersDTO();
        dto.setId(users.getId());
        dto.setUserName(users.getUserName());
        dto.setPassword(users.getPassword());
        return dto;
    }

    public static Users toEntity(UsersDTO dto) {
        Users users = new Users();
        users.setId(dto.getId());
        users.setUserName(dto.getUserName());
        users.setPassword(dto.getPassword());
        return users;
    }
}
