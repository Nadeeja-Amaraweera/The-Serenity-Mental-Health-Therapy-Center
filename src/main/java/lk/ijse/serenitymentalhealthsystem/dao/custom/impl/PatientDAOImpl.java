package lk.ijse.serenitymentalhealthsystem.dao.custom.impl;

import lk.ijse.serenitymentalhealthsystem.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealthsystem.entity.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public List<Patient> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean save(Patient entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Patient entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }
}
