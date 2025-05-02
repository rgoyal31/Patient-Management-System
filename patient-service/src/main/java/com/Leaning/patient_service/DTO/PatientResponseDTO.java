package com.Leaning.patient_service.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDTO {
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;
}
