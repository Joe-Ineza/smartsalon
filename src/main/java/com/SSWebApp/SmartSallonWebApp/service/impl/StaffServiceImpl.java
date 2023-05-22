package com.SSWebApp.SmartSallonWebApp.service.impl;

import com.SSWebApp.SmartSallonWebApp.Exceptions.ResourceNotFoundException;
import com.SSWebApp.SmartSallonWebApp.dto.StaffDTO;

import com.SSWebApp.SmartSallonWebApp.mapper.StaffMapper;
import com.SSWebApp.SmartSallonWebApp.models.Staff;
import com.SSWebApp.SmartSallonWebApp.repository.StaffRepository;
import com.SSWebApp.SmartSallonWebApp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository, StaffMapper staffMapper) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    public StaffDTO save(StaffDTO staffDTO) {
        Staff staff = staffMapper.toEntity(staffDTO);
        Staff savedStaff = staffRepository.save(staff);
        return staffMapper.toDto(savedStaff);
    }

    @Override
    public List<StaffDTO> findAll() {
        List<Staff> staffList = staffRepository.findAll();
        return staffList.stream().map(StaffMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StaffDTO findById(Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        return staffMapper.toDto(staff);
    }

    @Override
    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }
}
