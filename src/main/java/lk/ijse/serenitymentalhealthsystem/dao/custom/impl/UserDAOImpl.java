package lk.ijse.serenitymentalhealthsystem.dao.custom.impl;

import lk.ijse.serenitymentalhealthsystem.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealthsystem.dao.custom.UserDAO;
import lk.ijse.serenitymentalhealthsystem.entity.Role;
import lk.ijse.serenitymentalhealthsystem.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(User user) throws Exception, SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(user);
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
    public boolean login(String username, String password, Role role) throws SQLException {
        return false;
    }
}
