package com.Leaning.patient_service.Service;

import com.Leaning.patient_service.DTO.PatientResponseDTO;
import com.Leaning.patient_service.DTO.PatientResquestDTO;
import com.Leaning.patient_service.Mapper.PatientMapper;
import com.Leaning.patient_service.Model.Patient;
import com.Leaning.patient_service.Repo.PatientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PatientService {

    private final PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }

    public List<PatientResponseDTO> getAllPatient(){
        log.info("Starting the Service Class");
        List<Patient> patients = patientRepo.findAll();
        log.info("In the service class After the findable done in the RepoLayer");
        return patients.stream()
                .map(PatientMapper::ToResponseDTO)
                .toList();
    }

    public PatientResponseDTO registerPatient(PatientResquestDTO patientResquestDTO){
        Patient newPatient = patientRepo.save(PatientMapper.ToPatient(patientResquestDTO));
        return PatientMapper.ToResponseDTO(newPatient);
    }
}
