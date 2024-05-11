package com.exmple.patientsmvc.Service.ServiceImpl;

import com.exmple.patientsmvc.Service.IService.ConsultationService;
import com.exmple.patientsmvc.entities.Consultation;
import com.exmple.patientsmvc.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ConsultationServiceImpl implements ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation updateConsultation(Long id, Consultation consultation) {
        consultation.setId(id);  // Make sure the id is set correctly
        return consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long consultationId) {
        consultationRepository.deleteById(consultationId);
    }

    @Override
    public Optional<Consultation> getConsultation(Long id) {
        return consultationRepository.findById(id);
    }

    @Override
    public Page<Consultation> getAllConsultations(Pageable pageable) {
        return consultationRepository.findAll(pageable);
    }
}

