package lk.ijse.serenitymentalhealthsystem.dao.custom;

import lk.ijse.serenitymentalhealthsystem.dao.CrudDAO;
import lk.ijse.serenitymentalhealthsystem.entity.Role;
import lk.ijse.serenitymentalhealthsystem.entity.User;

import java.sql.SQLException;


public interface UserDAO extends CrudDAO<User> {
    boolean login(String username, String password, Role role) throws SQLException;
}
