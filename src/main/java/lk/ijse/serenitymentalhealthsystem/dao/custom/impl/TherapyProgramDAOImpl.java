package lk.ijse.serenitymentalhealthsystem.dao.custom.impl;

import lk.ijse.serenitymentalhealthsystem.dao.custom.TherapyProgramDAO;
import lk.ijse.serenitymentalhealthsystem.entity.TherapyProgram;

import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    @Override
    public List<TherapyProgram> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean save(TherapyProgram entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(TherapyProgram entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }
}
