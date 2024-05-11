package com.exmple.patientsmvc.Service.IService;

import com.exmple.patientsmvc.entities.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ConsultationService {
    Consultation saveConsultation(Consultation consultation);
    Consultation updateConsultation(Long id, Consultation consultation);
    void deleteConsultation(Long consultationId);
    Optional<Consultation> getConsultation(Long id);
    Page<Consultation> getAllConsultations(Pageable pageable);
}
