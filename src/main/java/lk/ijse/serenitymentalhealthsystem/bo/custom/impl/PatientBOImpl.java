package lk.ijse.serenitymentalhealthsystem.bo.custom.impl;

import lk.ijse.serenitymentalhealthsystem.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealthsystem.dao.DAOFactory;
import lk.ijse.serenitymentalhealthsystem.dao.DAOTypes;
import lk.ijse.serenitymentalhealthsystem.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealthsystem.dao.custom.impl.PatientDAOImpl;
import lk.ijse.serenitymentalhealthsystem.dto.PatientDTO;
import lk.ijse.serenitymentalhealthsystem.entity.Patient;

public class PatientBOImpl implements PatientBO {

    private final PatientDAO patientDAO = (PatientDAO)DAOFactory.getInstance().getDAO(DAOTypes.PATIENT);


    @Override
    public boolean savePatient(PatientDTO patientDTO) throws Exception {
        Patient  patient = new Patient();
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setGender(patientDTO.getGender());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setPhone(patientDTO.getPhone());
        patient.setStatus(patientDTO.getStatus());

        return patientDAO.save(patient);
    }
}
