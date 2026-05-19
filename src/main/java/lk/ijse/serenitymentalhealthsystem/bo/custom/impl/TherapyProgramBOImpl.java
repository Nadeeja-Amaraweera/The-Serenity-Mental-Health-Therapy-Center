package lk.ijse.serenitymentalhealthsystem.bo.custom.impl;

import lk.ijse.serenitymentalhealthsystem.bo.custom.TherapyProgramBO;
import lk.ijse.serenitymentalhealthsystem.dao.DAOFactory;
import lk.ijse.serenitymentalhealthsystem.dao.DAOTypes;
import lk.ijse.serenitymentalhealthsystem.dao.custom.TherapyProgramDAO;
import lk.ijse.serenitymentalhealthsystem.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealthsystem.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public class TherapyProgramBOImpl implements TherapyProgramBO {

    private final TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOTypes.THERAPY_PROGRAM);

    @Override
    public List<TherapyProgramDTO> getAllPrograms() throws Exception {
        List<TherapyProgram> therapyPrograms = therapyProgramDAO.getAll();
        List<TherapyProgramDTO> therapyProgramDTOs = new ArrayList<>();

        for (TherapyProgram program : therapyPrograms) {

            TherapyProgramDTO dto = new TherapyProgramDTO();
            dto.setProgramId(program.getProgramId());
            dto.setProgramName(program.getProgramName());
            dto.setTherapyType(program.getTherapyType());
            dto.setDuration(program.getDuration());
            dto.setFrequency(program.getFrequency());
            dto.setStartDate(program.getStartDate());
            dto.setEndDate(program.getEndDate());
            dto.setStatus(program.getStatus());
            dto.setDescription(program.getDescription());

        }
        return therapyProgramDTOs;
    }

    @Override
    public String getNextId() throws Exception {
        String lastId = therapyProgramDAO.getLastId();
        char tableChar = 'T';

        if (lastId == null) {
            return tableChar + "001";
        } else {
            int lastDigits = Integer.parseInt(lastId.substring(1));
            int nextDigits = lastDigits + 1;
            return String.format("%s%03d", tableChar, nextDigits);
        }
    }

    @Override
    public boolean saveProgram(TherapyProgramDTO therapyProgramDTO) throws Exception {
        TherapyProgram therapyProgram = new TherapyProgram();

        therapyProgram.setProgramId(therapyProgramDTO.getProgramId());
        therapyProgram.setProgramName(therapyProgramDTO.getProgramName());
        therapyProgram.setTherapyType(therapyProgramDTO.getTherapyType());
        therapyProgram.setDuration(therapyProgramDTO.getDuration());
        therapyProgram.setFrequency(therapyProgramDTO.getFrequency());
        therapyProgram.setStartDate(therapyProgramDTO.getStartDate());
        therapyProgram.setEndDate(therapyProgramDTO.getEndDate());
        therapyProgram.setStatus(therapyProgramDTO.getStatus());
        therapyProgram.setDescription(therapyProgramDTO.getDescription());

        return therapyProgramDAO.save(therapyProgram);
    }

    @Override
    public TherapyProgramDTO getProgramById(Long id) throws Exception {
        return null;
    }

    @Override
    public boolean updateProgram(TherapyProgramDTO therapyProgramDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteProgram(Long id) throws Exception {
        return false;
    }
}
