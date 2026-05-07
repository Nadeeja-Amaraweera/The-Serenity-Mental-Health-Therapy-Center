package lk.ijse.serenitymentalhealthsystem.dao.custom.impl;

import lk.ijse.serenitymentalhealthsystem.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealthsystem.dao.custom.UserDAO;
import lk.ijse.serenitymentalhealthsystem.entity.Role;
import lk.ijse.serenitymentalhealthsystem.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<User> getAll() throws Exception {
        return List.of();
    }

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
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public User findByUsername(String username, String password) throws SQLException {
        Session session = FactoryConfiguration
                .getInstance()
                .getSession();

        try {
            String hql = "FROM User WHERE username = :username AND password = :password";

            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.uniqueResult(); // returns null if not found

        } finally {
            session.close();
        }
    }
}
