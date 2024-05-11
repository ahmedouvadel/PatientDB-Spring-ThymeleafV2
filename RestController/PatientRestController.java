package com.exmple.patientsmvc.RestController;

import com.exmple.patientsmvc.Service.IService.PatientService;
import com.exmple.patientsmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/patients") // Define base path for all API endpoints
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public ResponseEntity<List<Patient>> getAllPatients(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> pagePatients = patientService.getAllPatients(pageable, keyword);
        return new ResponseEntity<>(pagePatients.getContent(), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}") // Use PathVariable for clearer routing
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Indicate successful deletion without content
    }

    @GetMapping("/")
    public String home() {
        // Remove this method as it doesn't provide a REST API endpoint
        return null;
    }

    @GetMapping("/edit/{id}") // Use PathVariable for clearer routing
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatient(id);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Indicate resource not found
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        // Use @RequestBody to handle request body content
        patientService.savePatient(patient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED); // Indicate successful creation
    }

    @PutMapping("/edit/{id}") // Use PUT for updates (ideally)
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        // Use @RequestBody to handle request body content
        patient.setId(id); // Ensure ID matches path variable
        patientService.savePatient(patient);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}
