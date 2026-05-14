package lk.ijse.serenitymentalhealthsystem.dao.custom.impl;

import lk.ijse.serenitymentalhealthsystem.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealthsystem.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealthsystem.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public List<Patient> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();

        List<Patient> list = session.createQuery("from Patient", Patient.class).list();

        session.close();
        return list;
    }

    @Override
    public boolean save(Patient entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }


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
