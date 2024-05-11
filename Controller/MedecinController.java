package com.exmple.patientsmvc.Controller;

import com.exmple.patientsmvc.Service.IService.MedecinService;
import com.exmple.patientsmvc.entities.Medecin;
import com.exmple.patientsmvc.entities.Patient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class MedecinController {

    private final MedecinService medecinService;

    @GetMapping("/medecins")
    public String medecins(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Medecin> pageMedecins = medecinService.getAllMedecins(pageable, keyword);
        model.addAttribute("listMedecins", pageMedecins.getContent());
        model.addAttribute("pages", new int[pageMedecins.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "medecins";
    }

    @GetMapping("/deleteMedecin")
    public String delete(Long id, String keyword, int page) {
        medecinService.deleteMedecin(id);
        return "redirect:/medecins?page=" + page + "&keyword=" + keyword;
    }


    @GetMapping("/medecinForm")
    public String medecinForm(Model model) {
        model.addAttribute("medecin", new Medecin());
        return "medecinForm";
    }

    @PostMapping(path = "/saveMedecin")
    public String save(Model model, @Valid Medecin medecin, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) return "formMedecin";
        medecinService.saveMedecin(medecin);
        return "redirect:/medecins?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/editMedecin")
    public String editMedecin(Model model, Long id, String keyword, int page) {
        Medecin medecin = medecinService.getMedecin(id);
        if (medecin == null) throw new RuntimeException("Medecin introuvable");
        model.addAttribute("medecin", medecin);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editMedecin";
    }
}
