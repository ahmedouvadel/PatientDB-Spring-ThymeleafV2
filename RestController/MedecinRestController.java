package com.exmple.patientsmvc.RestController;

import com.exmple.patientsmvc.Service.IService.MedecinService;
import com.exmple.patientsmvc.entities.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/medecins") // Define base path for all API endpoints
public class MedecinRestController {


    private final MedecinService medecinService;

    public MedecinRestController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Medecin>> getAllMedecins(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Medecin> pageMedecins = medecinService.getAllMedecins(pageable, keyword);
        return new ResponseEntity<>(pageMedecins.getContent(), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}") // Use PathVariable for clearer routing
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Indicate successful deletion without content
    }

    @GetMapping("/add") // Consider using POST for creating resources
    public ResponseEntity<Medecin> createMedecin( @RequestBody Medecin medecin) {
        // Use @RequestBody to handle request body content
        medecinService.saveMedecin(medecin);
        return new ResponseEntity<>(medecin, HttpStatus.CREATED); // Indicate successful creation
    }

    @GetMapping("/edit/{id}") // Use PUT for updates (ideally)
    public ResponseEntity<Medecin> getMedecin(@PathVariable Long id) {
        Medecin medecin = medecinService.getMedecin(id);
        if (medecin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Indicate resource not found
        }
        return new ResponseEntity<>(medecin, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}") // Use PUT for updates (ideally)
    public ResponseEntity<Medecin> updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        // Use @RequestBody to handle request body content
        medecin.setId(id); // Ensure ID matches path variable
        medecinService.saveMedecin(medecin);
        return new ResponseEntity<>(medecin, HttpStatus.OK);
    }
}
