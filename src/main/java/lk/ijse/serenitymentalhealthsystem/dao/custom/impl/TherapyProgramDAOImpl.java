package lk.ijse.serenitymentalhealthsystem.dao.custom.impl;

import lk.ijse.serenitymentalhealthsystem.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealthsystem.dao.SQLUtil;
import lk.ijse.serenitymentalhealthsystem.dao.custom.TherapyProgramDAO;
import lk.ijse.serenitymentalhealthsystem.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<TherapyProgram> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<TherapyProgram> list = session.createQuery("from TherapyProgram", TherapyProgram.class).list();
        session.close();
        return list;
    }

    @Override
    public boolean save(TherapyProgram entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try{
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
    public boolean update(TherapyProgram entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(entity);
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
    public boolean delete(Long id) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram therapyProgram = session.get(TherapyProgram.class, id);
            if (therapyProgram != null) {
                session.remove(therapyProgram);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws Exception {
        Session session = factoryConfiguration.getSession();

        try {

            String hql = "SELECT c.programId FROM TherapyProgram c ORDER BY c.programId DESC";

            Query<String> query = session.createQuery(hql, String.class);

            query.setMaxResults(1);

            return query.uniqueResult();

        } finally {
            session.close();
        }
    }
}
