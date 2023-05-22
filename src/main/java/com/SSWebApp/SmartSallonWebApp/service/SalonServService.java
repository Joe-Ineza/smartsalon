package com.SSWebApp.SmartSallonWebApp.service;

import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.dto.SalonServDTO;

import java.util.List;

public interface SalonServService {

    SalonServDTO createSalonServDto(SalonServDTO salonServDTO);
    SalonServDTO getSalonServById(Long id);
    SalonServDTO updateSalonServ(Long id, SalonServDTO salonServDTO);
    void deleteSalonServById(Long id);
    List<SalonServDTO> getAllSalonServ();

}
