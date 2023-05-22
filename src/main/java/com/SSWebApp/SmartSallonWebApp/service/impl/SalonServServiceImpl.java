package com.SSWebApp.SmartSallonWebApp.service.impl;

import com.SSWebApp.SmartSallonWebApp.Exceptions.ResourceNotFoundException;
import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.dto.SalonServDTO;
import com.SSWebApp.SmartSallonWebApp.mapper.SalonServMapper;
import com.SSWebApp.SmartSallonWebApp.models.SalonServ;
import com.SSWebApp.SmartSallonWebApp.repository.SalonServRepository;
import com.SSWebApp.SmartSallonWebApp.service.SalonServService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalonServServiceImpl implements SalonServService {

    private final SalonServRepository salonServRepository;
    private final SalonServMapper salonServMapper;

    public SalonServServiceImpl(SalonServRepository salonServRepository, SalonServMapper salonServMapper) {
        this.salonServRepository = salonServRepository;
        this.salonServMapper = salonServMapper;
    }


    @Override
    public SalonServDTO createSalonServDto(SalonServDTO salonServDTO) {
        SalonServ salonServ = salonServMapper.toEntity(salonServDTO);
        SalonServ savedSalonServ = salonServRepository.save(salonServ);
        return salonServMapper.toDto(savedSalonServ);
    }

    @Override
    public SalonServDTO getSalonServById(Long id) {
        SalonServ salonServ = salonServRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SalonServ", "id", id));
        return salonServMapper.toDto(salonServ);
    }

    @Override
    public SalonServDTO updateSalonServ(Long id, SalonServDTO salonServDTO) {
        SalonServ existingSalonServ = salonServRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SalonServ","id", id));
        SalonServ updatedSalonServ = salonServMapper.toEntity(salonServDTO);
        updatedSalonServ.setId(existingSalonServ.getId());
        SalonServ savedSalonServ = salonServRepository.save(updatedSalonServ);
        return salonServMapper.toDto(savedSalonServ);
    }

    @Override
    public void deleteSalonServById(Long id) {
        if (!salonServRepository.existsById(id)) {
            throw new ResourceNotFoundException("SalonServ", "id", id);
        }
        salonServRepository.deleteById(id);
    }

    @Override
    public List<SalonServDTO> getAllSalonServ() {
        List<SalonServ> salonServ = salonServRepository.findAll();
        return salonServ.stream().map(SalonServMapper::toDto).collect(Collectors.toList());
    }


}
