package com.Leaning.patient_service.Repo;

import com.Leaning.patient_service.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepo extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);
}
