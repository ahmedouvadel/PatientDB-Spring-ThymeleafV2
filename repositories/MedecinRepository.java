package com.exmple.patientsmvc.repositories;

import com.exmple.patientsmvc.entities.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Page<Medecin> findByNomContaining(String nom, Pageable pageable);

}
