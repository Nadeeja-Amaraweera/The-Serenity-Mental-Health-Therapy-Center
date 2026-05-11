package lk.ijse.serenitymentalhealthsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    // Personal Information
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String gender;

    // Contact Information
//    private String email;
    private String phone;
//    private String address;
//    private String city;
//    private String state;
//
//    // Medical Information
//    private String bloodType;
//    private String allergies;
//
//    @Column(length = 2000)
//    private String medicalHistory;
//
//    // Therapy Information
//    private String primaryConcern;
//    private String therapyType;
    private String status;
//
//    @Column(length = 1500)
//    private String notes;
//
//    // Emergency Contact
//    private String emergencyName;
//    private String emergencyPhone;
//    private String relationship;

}
