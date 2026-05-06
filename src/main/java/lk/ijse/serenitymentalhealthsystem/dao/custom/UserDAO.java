package lk.ijse.serenitymentalhealthsystem.dao.custom;

import lk.ijse.serenitymentalhealthsystem.dao.CrudDAO;
import lk.ijse.serenitymentalhealthsystem.entity.Role;
import lk.ijse.serenitymentalhealthsystem.entity.User;

import java.sql.SQLException;


public interface UserDAO extends CrudDAO<User> {

    User findByUsername(String username, String password) throws SQLException;
}
