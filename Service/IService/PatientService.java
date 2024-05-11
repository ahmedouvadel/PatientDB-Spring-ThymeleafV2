package com.exmple.patientsmvc.Service.IService;

import com.exmple.patientsmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    Page<Patient> getAllPatients(Pageable pageable, String keyword);
    void deletePatient(Long id);
    Patient savePatient(Patient patient);
    Patient getPatient(Long id);
}
