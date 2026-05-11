package lk.ijse.serenitymentalhealthsystem.bo.custom;

import lk.ijse.serenitymentalhealthsystem.bo.SuperBO;
import lk.ijse.serenitymentalhealthsystem.dto.PatientDTO;
import lk.ijse.serenitymentalhealthsystem.entity.Patient;

public interface PatientBO extends SuperBO {

    boolean savePatient(PatientDTO patientDTO) throws Exception;
}
