package com.SSWebApp.SmartSallonWebApp.service.impl;
import com.SSWebApp.SmartSallonWebApp.models.Role;
import com.SSWebApp.SmartSallonWebApp.models.Users;
import com.SSWebApp.SmartSallonWebApp.repository.RoleRepository;
import com.SSWebApp.SmartSallonWebApp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsersRepository usersRepository;


    public List<Role> findAll() {
        return roleRepository.findAll();
    }


    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }


    public void delete(Long id) {
        roleRepository.deleteById(id);
    }


    public void save(Role role) {
        roleRepository.save(role);
    }

    public void assignUserRole(Long userId, Long roleId) {
        Users users = usersRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);

        if (users != null && role != null) {
            Set<Role> usersRoles = users.getRoles();
            usersRoles.add(role);
            users.setRoles(usersRoles);
            usersRepository.save(users);
        }
    }

    public void unassignUserRole(Long userId, Long roleId) {
        Users users = usersRepository.findById(userId).orElse(null);
        users.getRoles().removeIf(x -> x.getId() == roleId);
        usersRepository.save(users);
    }
    public Set<Role> getUserRoles(Users users) {
        return users.getRoles();
    }
    public List<Role> getUserNotRoles(Users users){
        return roleRepository.getUserNotRoles(users.getId());
    }
}
