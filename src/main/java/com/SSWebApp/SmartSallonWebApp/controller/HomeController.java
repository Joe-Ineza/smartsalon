package com.SSWebApp.SmartSallonWebApp.controller;

import com.SSWebApp.SmartSallonWebApp.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.SSWebApp.SmartSallonWebApp.mapper.CustomerMapper;
import com.SSWebApp.SmartSallonWebApp.mapper.StaffMapper;
import com.SSWebApp.SmartSallonWebApp.mapper.UsersMapper;
import com.SSWebApp.SmartSallonWebApp.repository.CustomerRepository;
import com.SSWebApp.SmartSallonWebApp.repository.StaffRepository;
import com.SSWebApp.SmartSallonWebApp.repository.UsersRepository;
import com.SSWebApp.SmartSallonWebApp.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UsersService usersService;

    private final StaffRepository staffRepository;
    private  final StaffMapper staffMapper;

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private  final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public HomeController(UsersService usersService, StaffRepository staffRepository, StaffMapper staffMapper, CustomerRepository customerRepository, CustomerMapper customerMapper, UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersService = usersService;
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @RequestMapping("/index")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }



    @RequestMapping("/logout-success")
    public String logoutPage() {
        return "redirect:/index";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }


    @GetMapping("/register")
    public String showNewCustomerForm(Model model) {
//        CustomerDTO customerdto = new CustomerDTO();
//        StaffDTO staffdto = new StaffDTO();
//        UsersDTO userssdto = new UsersDTO();
//        modelCust.addAttribute("customer",customerdto);
//        modelStaff.addAttribute("staff",staffdto);
//        modelUsers.addAttribute("users",userssdto);
//        modelCust.addAttribute()
//        model.addAttribute("customer", customer);
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("registration",registrationDTO);

        return "register";
    }




    @PostMapping("/register")
    public String createCustomer(@ModelAttribute("registration") @Valid RegistrationDTO registrationDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }


        UsersDTO usersDTO = new UsersDTO();
        List<UsersDTO> usersList = usersService.getAllUsersByUserName(registrationDTO.getEmail());
        if (registrationDTO.getEmail().contains("acmemply.7")) {

            if (usersList.isEmpty()) {
                StaffDTO staffDTO = new StaffDTO();
                staffDTO.setName(registrationDTO.getName());
                staffDTO.setEmail(registrationDTO.getEmail());
                usersDTO.setUserName(registrationDTO.getEmail());
                usersDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
                staffDTO.setPhoneNumber(registrationDTO.getPhoneNumber());
                staffRepository.save(staffMapper.toEntity(staffDTO));
                usersRepository.save(usersMapper.toEntity(usersDTO));
                return "redirect:/login";
            } else {

                return "redirect:/register";

            }
        } else if (usersList.isEmpty()) {


            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setName(registrationDTO.getName());
            customerDTO.setEmail(registrationDTO.getEmail());
            customerDTO.setPhoneNumber(registrationDTO.getPhoneNumber());
            customerRepository.save(customerMapper.toEntity(customerDTO));
            usersDTO.setUserName(registrationDTO.getEmail());
            usersDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            usersRepository.save(usersMapper.toEntity(usersDTO));
            return "redirect:/login";
        } else {
            return "redirect:/register";
        }

    }
}



//    @GetMapping("/confirm-login")
//    public String showConfirmLoginPage() {
//        return "confirm-login";
//    }

