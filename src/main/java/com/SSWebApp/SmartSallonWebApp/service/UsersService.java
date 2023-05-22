package com.SSWebApp.SmartSallonWebApp.service;

import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.dto.UsersDTO;
import com.SSWebApp.SmartSallonWebApp.models.Users;

import java.util.List;

public interface UsersService {
    UsersDTO createUsers(UsersDTO usersDTO);

    List<Users> getAllUsers();



    List<UsersDTO> getAllUsersByUserName(String username);
}
