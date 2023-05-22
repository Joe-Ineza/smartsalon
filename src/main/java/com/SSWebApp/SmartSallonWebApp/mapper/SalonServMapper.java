package com.SSWebApp.SmartSallonWebApp.mapper;
import com.SSWebApp.SmartSallonWebApp.dto.SalonServDTO;
import com.SSWebApp.SmartSallonWebApp.models.SalonServ;
import org.springframework.stereotype.Component;

@Component
public class SalonServMapper {
    public static SalonServDTO toDto(SalonServ salonServ) {
        SalonServDTO dto = new SalonServDTO();
        dto.setId(salonServ.getId());
        dto.setGender(salonServ.getGender());
        dto.setServiceOffered(salonServ.getServiceOffered());
        dto.setPrice(salonServ.getPrice());
        return dto;
    }

    public static SalonServ toEntity(SalonServDTO dto) {
        SalonServ salonServ = new SalonServ();
        salonServ.setId(dto.getId());
        salonServ.setGender(dto.getGender());
        salonServ.setServiceOffered(dto.getServiceOffered());
        salonServ.setPrice(dto.getPrice());

        return salonServ;
    }
}
