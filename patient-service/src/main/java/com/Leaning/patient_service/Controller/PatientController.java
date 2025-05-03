package com.Leaning.patient_service.Controller;

import com.Leaning.patient_service.DTO.PatientResponseDTO;
import com.Leaning.patient_service.DTO.PatientResquestDTO;
import com.Leaning.patient_service.DTO.validators.CreatePatientValidationGroup;
import com.Leaning.patient_service.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/patient")
@Slf4j
@Tag(name = "Patients", description = "Api for managing Patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping(path = "/getAll")
    @Operation(summary = "Get All Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        log.info("Inside the getAllPatientMethods in the controller");
        return ResponseEntity.ok(patientService.getAllPatient());
    }

    @PostMapping(path = "/create")
    @Operation(summary = "Create a New Patients")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientResquestDTO patientResquestDTO){
        return ResponseEntity.ok(patientService.registerPatient(patientResquestDTO));
    }

    @PutMapping(path = "/update/{email}")
    @Operation(summary = "Update the Patients")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable String email,
                                                            @Validated({Default.class}) @RequestBody PatientResquestDTO patientResquestDTO){
        return ResponseEntity.ok(patientService.updatePatient(email,patientResquestDTO));
    }

    @DeleteMapping(path = "/{email}")
    @Operation(summary = "Delete Patients")
    public ResponseEntity<?> deletePatient(@PathVariable String email){
        patientService.deletePatient(email);
        return ResponseEntity.ok("Deletion SuccessFull");
    }
}
