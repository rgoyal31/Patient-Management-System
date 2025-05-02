package com.Leaning.patient_service.Controller;

import com.Leaning.patient_service.DTO.PatientResponseDTO;
import com.Leaning.patient_service.DTO.PatientResquestDTO;
import com.Leaning.patient_service.Service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/patient")
@Slf4j
public class PatientController {

    private final PatientService patientService;
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        log.info("Inside the getAllPatientMethods in the controller");
        return ResponseEntity.ok(patientService.getAllPatient());
    }

    @PostMapping(path = "/create")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientResquestDTO patientResquestDTO){
        return ResponseEntity.ok(patientService.registerPatient(patientResquestDTO));
    }
}
