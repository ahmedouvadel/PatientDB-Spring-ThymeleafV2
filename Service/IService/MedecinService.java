package com.exmple.patientsmvc.Service.IService;

import com.exmple.patientsmvc.entities.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MedecinService {
    Medecin saveMedecin(Medecin medecin);
    Medecin updateMedecin(Long id, Medecin medecin);
    void deleteMedecin(Long id);
    Medecin getMedecin(Long id);
    List<Medecin> getAllMedecins();
    Page<Medecin> getAllMedecins(Pageable pageable,String keyword);
}

