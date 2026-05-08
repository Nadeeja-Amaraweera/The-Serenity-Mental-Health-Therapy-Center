package lk.ijse.serenitymentalhealthsystem.dto;



import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PatientDTO {
    private Long patientId;

    // Personal Information
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;

    // Contact Information
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;

    // Medical Information
    private String bloodType;
    private String allergies;

    private String medicalHistory;

    // Therapy Information
    private String primaryConcern;
    private String therapyType;
    private String status;

    private String notes;

    // Emergency Contact
    private String emergencyName;
    private String emergencyPhone;
    private String relationship;
}
