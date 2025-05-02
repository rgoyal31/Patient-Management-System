package com.Leaning.patient_service.Service;

import com.Leaning.patient_service.DTO.PatientResponseDTO;
import com.Leaning.patient_service.DTO.PatientResquestDTO;
import com.Leaning.patient_service.Exception.EmailAlreadyExistsException;
import com.Leaning.patient_service.Exception.PatientNotFoundException;
import com.Leaning.patient_service.Mapper.PatientMapper;
import com.Leaning.patient_service.Model.Patient;
import com.Leaning.patient_service.Repo.PatientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        if(patientRepo.existsByEmail(patientResquestDTO.getEmail())){
            //will throw a custom exception
            throw new EmailAlreadyExistsException("A Patient with the email already exist " + patientResquestDTO.getEmail());
        }
        Patient newPatient = patientRepo.save(PatientMapper.ToPatient(patientResquestDTO));
        return PatientMapper.ToResponseDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(String email, PatientResquestDTO patientResquestDTO){
//        if(!patientRepo.existsByEmail(email)){
//            throw new EmailNotExistException("Patient associated with the email {} doesn't exist in the DB", email);
//        }
        Patient patient = patientRepo.findByEmail(email).orElseThrow(
                ()-> new PatientNotFoundException("Patient associated with the email doesn't exist in the DB " + email));

        patient.setName(patientResquestDTO.getName());
        patient.setEmail(patientResquestDTO.getEmail());
        patient.setAddress(patientResquestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientResquestDTO.getDateOfBirth()));

        patientRepo.save(patient);

        return PatientMapper.ToResponseDTO(patient);
    }
}
