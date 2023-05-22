package com.SSWebApp.SmartSallonWebApp.service;

import com.SSWebApp.SmartSallonWebApp.dto.StaffDTO;

import java.util.List;

public interface StaffService {
    StaffDTO save(StaffDTO staffDTO);
    List<StaffDTO> findAll();
    StaffDTO findById(Long id);
    void deleteById(Long id);
}
