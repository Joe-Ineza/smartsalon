package com.SSWebApp.SmartSallonWebApp.service.impl;

import com.SSWebApp.SmartSallonWebApp.dto.UsersDTO;
import com.SSWebApp.SmartSallonWebApp.mapper.UsersMapper;
import com.SSWebApp.SmartSallonWebApp.models.Users;
import com.SSWebApp.SmartSallonWebApp.repository.UsersRepository;
import com.SSWebApp.SmartSallonWebApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @Override
    public UsersDTO createUsers(UsersDTO usersDTO) {
        Users users = usersMapper.toEntity(usersDTO);
        Users savedUsers = usersRepository.save(users);
        return usersMapper.toDto(savedUsers);
    }

    @Override
    public List<Users> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users;
    }

    @Override
    public List<UsersDTO> getAllUsersByUserName(String username) {
        List<Users> users = usersRepository.findAllByUserName(username);
        return users.stream().map(UsersMapper::toDto).collect(Collectors.toList());
    }
}
