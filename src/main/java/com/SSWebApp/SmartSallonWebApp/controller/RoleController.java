package com.SSWebApp.SmartSallonWebApp.controller;

import com.SSWebApp.SmartSallonWebApp.models.Role;
import com.SSWebApp.SmartSallonWebApp.models.Users;
import com.SSWebApp.SmartSallonWebApp.service.UsersService;
import com.SSWebApp.SmartSallonWebApp.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {
    private final RoleService roleService;
    private final UsersService usersService;

    @Autowired
    public RoleController(RoleService roleService, UsersService usersService) {
        this.roleService = roleService;
        this.usersService = usersService;
    }
    @GetMapping("/manager")
    public String managing(Model model)
    {
        return"Manager";
    }

    @GetMapping("/security/roles")
    public String parameters(Model model) {
        List<Role> roles = roleService.findAll();
//        List<Users> users = usersService.getAllUsers();
//        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "security-roles";
    }

    @GetMapping("/security/role/{id}")
    @ResponseBody
    public Role getById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @GetMapping("/security/roles/create")
    public String createRole(Model model) {
        List<Users> users = usersService.getAllUsers(); // Fetch the list of users
        model.addAttribute("users", users);
        model.addAttribute("role", new Role());
        return "create-role";
    }

    @PostMapping("/security/roles")
    public String save(@ModelAttribute Role role, @RequestParam("userId") Long userId) {
        roleService.save(role);
        roleService.assignUserRole(userId, role.getId());
        return "redirect:/security/roles";
    }


    @GetMapping("/security/roles/edit/{id}")
    public String editRole(@PathVariable Long id, Model model) {
        Role role = roleService.findById(id);
        List<Users> users = usersService.getAllUsers();
        model.addAttribute("role", role);
        model.addAttribute("users", users);
        return "edit-role";
    }


    @PostMapping("/security/roles/edit/{id}")
    public String updateRole(@PathVariable Long id, @ModelAttribute Role role, @RequestParam("userId") Long userId) {
        role.setId(id);
        roleService.save(role);
        roleService.assignUserRole(userId, role.getId());
        return "redirect:/security/roles";
    }

    @DeleteMapping("/security/roles/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/security/roles";
    }
}
