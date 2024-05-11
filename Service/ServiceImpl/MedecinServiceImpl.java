package com.exmple.patientsmvc.Service.ServiceImpl;

import com.exmple.patientsmvc.Service.IService.MedecinService;
import com.exmple.patientsmvc.entities.Medecin;
import com.exmple.patientsmvc.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedecinServiceImpl implements MedecinService {
    @Autowired
    private MedecinRepository medecinRepository;

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public Medecin updateMedecin(Long id, Medecin medecin) {
        medecin.setId(id);  // Assurez-vous que l'identifiant est correctement configuré
        return medecinRepository.save(medecin);
    }

    @Override
    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }

    @Override
    public Medecin getMedecin(Long id) {
        return medecinRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public Page<Medecin> getAllMedecins(Pageable pageable, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return medecinRepository.findByNomContaining(keyword, pageable);
        } else {
            return medecinRepository.findAll(pageable);
        }
    }
}

