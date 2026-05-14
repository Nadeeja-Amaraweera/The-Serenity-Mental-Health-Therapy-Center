package lk.ijse.serenitymentalhealthsystem.bo.custom.impl;

import lk.ijse.serenitymentalhealthsystem.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealthsystem.dao.DAOFactory;
import lk.ijse.serenitymentalhealthsystem.dao.DAOTypes;
import lk.ijse.serenitymentalhealthsystem.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealthsystem.dto.PatientDTO;
import lk.ijse.serenitymentalhealthsystem.entity.Patient;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {

    private final PatientDAO patientDAO = (PatientDAO)DAOFactory.getInstance().getDAO(DAOTypes.PATIENT);


    @Override
    public boolean savePatient(PatientDTO patientDTO) throws Exception {
        Patient  patient = new Patient();
        // Personal Information
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setGender(patientDTO.getGender());

        // Contact Information
        patient.setEmail(patientDTO.getEmail());
        patient.setPhone(patientDTO.getPhone());
        patient.setAddress(patientDTO.getAddress());
        patient.setCity(patientDTO.getCity());
        patient.setState(patientDTO.getState());

        // Medical Information
        patient.setBloodType(patientDTO.getBloodType());
        patient.setAllergies(patientDTO.getAllergies());
        patient.setMedicalHistory(patientDTO.getMedicalHistory());

        // Therapy Information
        patient.setPrimaryConcern(patientDTO.getPrimaryConcern());
        patient.setTherapyType(patientDTO.getTherapyType());
        patient.setStatus(patientDTO.getStatus());
        patient.setNotes(patientDTO.getNotes());

        // Emergency Contact
        patient.setEmergencyName(patientDTO.getEmergencyName());
        patient.setEmergencyPhone(patientDTO.getEmergencyPhone());
        patient.setRelationship(patientDTO.getRelationship());

        return patientDAO.save(patient);
    }

    @Override
    public List<PatientDTO> getAllPatients() throws Exception {

        List<Patient> patientList = patientDAO.getAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();

        for (Patient p : patientList) {

            patientDTOList.add(new PatientDTO(
                    p.getPatientId(),
                    p.getFirstName(),
                    p.getLastName(),
                    p.getDateOfBirth(),
                    p.getGender(),
                    p.getEmail(),
                    p.getPhone(),
                    p.getAddress(),
                    p.getCity(),
                    p.getState(),
                    p.getBloodType(),
                    p.getAllergies(),
                    p.getMedicalHistory(),
                    p.getPrimaryConcern(),
                    p.getTherapyType(),
                    p.getStatus(),
                    p.getNotes(),
                    p.getEmergencyName(),
                    p.getEmergencyPhone(),
                    p.getRelationship()
            ));
        }
        return patientDTOList;
    }
}
