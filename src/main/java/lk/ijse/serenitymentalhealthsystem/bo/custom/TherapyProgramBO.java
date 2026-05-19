package lk.ijse.serenitymentalhealthsystem.bo.custom;

import lk.ijse.serenitymentalhealthsystem.bo.SuperBO;
import lk.ijse.serenitymentalhealthsystem.dto.TherapyProgramDTO;

import java.util.List;

public interface TherapyProgramBO extends SuperBO {

    List<TherapyProgramDTO> getAllPrograms() throws Exception;

    String getNextId() throws Exception;

    boolean saveProgram(TherapyProgramDTO therapyProgramDTO) throws Exception;

     TherapyProgramDTO getProgramById(Long id) throws Exception;

     boolean updateProgram(TherapyProgramDTO therapyProgramDTO) throws Exception;

     boolean deleteProgram(String id) throws Exception;
}
