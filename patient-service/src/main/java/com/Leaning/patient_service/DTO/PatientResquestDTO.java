package com.Leaning.patient_service.DTO;

import com.Leaning.patient_service.DTO.validators.CreatePatientValidationGroup;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResquestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 15, message = "name can't be more then 15 char long")
    private String name;

    @NotBlank(groups = CreatePatientValidationGroup.class,message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Date of Birth is required")
    private String dateOfBirth;
}
