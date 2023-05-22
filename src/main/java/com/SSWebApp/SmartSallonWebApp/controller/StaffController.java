package com.SSWebApp.SmartSallonWebApp.controller;

import com.SSWebApp.SmartSallonWebApp.Exceptions.ResourceNotFoundException;
import com.SSWebApp.SmartSallonWebApp.dto.StaffDTO;
import com.SSWebApp.SmartSallonWebApp.mapper.StaffMapper;
import com.SSWebApp.SmartSallonWebApp.models.Staff;
import com.SSWebApp.SmartSallonWebApp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;

    //@Autowired
    //private StaffMapper staffMapper;

    @GetMapping("/staffs")
    public String getAllStaff(Model model) {
        List<StaffDTO> staffList = staffService.findAll();
        //List<StaffDTO> staffDTOList = staffMapper.toDtoList(staffList);
        model.addAttribute("staffList", staffList);
        return "staff_List";
    }

    @GetMapping("staff/{id}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable(value = "id") Long staffId) throws ResourceNotFoundException {
        StaffDTO staff = staffService.findById(staffId);
        //StaffDTO staffDTO = staffMapper.toDto(staff);
        return ResponseEntity.ok().body(staff);
    }

    @GetMapping("staffs/newS")
    public String showNewStaffForm(Model model) {
        StaffDTO staffDTO = new StaffDTO();
        model.addAttribute("staffDTO", staffDTO);
        return "new_staff";
    }

    @PostMapping("staffs/newS")
    public String createStaff(@ModelAttribute("staffDTO") @Valid StaffDTO staffDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "new_staff";
        }

        //Staff staff = staffMapper.toEntity(staffDTO);
        staffService.save(staffDTO);
        return "redirect:/staffs";
    }

    @GetMapping("/staff/{id}/edit")
    public String showEditStaffForm(@PathVariable(value = "id") Long staffId, Model model) throws ResourceNotFoundException {
        StaffDTO staff = staffService.findById(staffId);
        //StaffDTO staffDTO = staffMapper.toDto(staff);
        model.addAttribute("staffDTO", staff);
        return "edit_staff";
    }

    @PostMapping("/staff/{id}/edit")
    public String updateStaff(@PathVariable(value = "id") Long staffId, @Valid @ModelAttribute("staffDTO") StaffDTO staffDTO, BindingResult result) throws ResourceNotFoundException {
        if (result.hasErrors()) {
            return "edit_staff";
        }

        //Staff staff = staffMapper.toEntity(staffDTO);
        //staff.setId(staffId);
        staffService.save(staffDTO);
        return "redirect:/staffs";
    }

    @DeleteMapping("/staff/{id}")
    public Map<String, Boolean> deleteStaff(@PathVariable(value = "id") Long staffId) throws ResourceNotFoundException {
        staffService.deleteById(staffId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
