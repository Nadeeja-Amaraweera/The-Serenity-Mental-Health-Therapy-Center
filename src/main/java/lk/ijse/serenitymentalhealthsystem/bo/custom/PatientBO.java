package lk.ijse.serenitymentalhealthsystem.bo.custom;

import lk.ijse.serenitymentalhealthsystem.bo.SuperBO;
import lk.ijse.serenitymentalhealthsystem.dto.PatientDTO;

import java.util.List;

public interface PatientBO extends SuperBO {

    boolean savePatient(PatientDTO patientDTO) throws Exception;

    List<PatientDTO> getAllPatients() throws Exception;

    boolean updatePatient(PatientDTO patientDTO) throws Exception;

    boolean deletePatient(Long id) throws Exception;
}
