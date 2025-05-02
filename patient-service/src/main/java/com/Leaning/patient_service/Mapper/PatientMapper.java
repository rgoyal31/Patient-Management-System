package com.Leaning.patient_service.Mapper;

import com.Leaning.patient_service.DTO.PatientResponseDTO;
import com.Leaning.patient_service.DTO.PatientResquestDTO;
import com.Leaning.patient_service.Model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO ToResponseDTO(Patient patient){

        return PatientResponseDTO.builder()
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .dateOfBirth(patient.getDateOfBirth().toString())
                .build();
    }

    public static Patient ToPatient(PatientResquestDTO patientResquestDTO){
        Patient patient = new Patient();
        patient.setName(patientResquestDTO.getName());
        patient.setEmail(patientResquestDTO.getEmail());
        patient.setAddress(patientResquestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse( patientResquestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.now());
        return patient;
    }
}
