package com.exmple.patientsmvc.Service.ServiceImpl;

import com.exmple.patientsmvc.Service.IService.PatientService;
import com.exmple.patientsmvc.entities.Patient;
import com.exmple.patientsmvc.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {


    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Page<Patient> getAllPatients(Pageable pageable, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return patientRepository.findByNomContains(keyword, pageable);
        } else {
            return patientRepository.findAll(pageable);
        }
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
}
