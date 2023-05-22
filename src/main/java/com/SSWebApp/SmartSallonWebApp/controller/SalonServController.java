package com.SSWebApp.SmartSallonWebApp.controller;

import com.SSWebApp.SmartSallonWebApp.dto.SalonServDTO;
import com.SSWebApp.SmartSallonWebApp.models.Customer;
import com.SSWebApp.SmartSallonWebApp.service.SalonServService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class SalonServController {

    private final SalonServService salonServService;

    public SalonServController(SalonServService salonServService) {
        this.salonServService = salonServService;
    }

    @GetMapping("salonServ/create")
    public String showCreateForm(Model model) {
        SalonServDTO salonServDTO = new SalonServDTO();
        model.addAttribute("salonServDTO", salonServDTO);
        return "salonServ-create";
    }

    @PostMapping("salonServ/create")
    public String createSalonServ(@ModelAttribute SalonServDTO salonServDTO) {
        salonServService.createSalonServDto(salonServDTO);
        return "redirect:/salonServs";
    }

    @GetMapping("salonServ/{id}")
    public String getSalonServById(@PathVariable Long id, Model model) {
        SalonServDTO salonServDTO = salonServService.getSalonServById(id);
        model.addAttribute("salonServ", salonServDTO);
        return "redirect:/salonServs";
    }

    @GetMapping("salonServ/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        SalonServDTO salonServDTO = salonServService.getSalonServById(id);
        model.addAttribute("salonServ", salonServDTO);
        return "salonServ-edit";
    }

    @PostMapping("salonServ/{id}/edit")
    public String updateSalonServ(@PathVariable Long id, @ModelAttribute SalonServDTO salonServDTO) {
        salonServService.updateSalonServ(id, salonServDTO);
        return "redirect:/salonServ/" + id;
    }

    @PostMapping("salonServ/{id}/delete")
    public String deleteSalonServById(@PathVariable Long id) {
        salonServService.deleteSalonServById(id);
        return "redirect:/salonServs";
    }

    @GetMapping("/salonServs")
    public String getAllSalonServ(Model model) {
        List<SalonServDTO> salonServList = salonServService.getAllSalonServ();
        model.addAttribute("salonServList", salonServList);
        return "salonServ_List";
    }
}
