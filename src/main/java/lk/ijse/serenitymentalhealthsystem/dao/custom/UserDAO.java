package lk.ijse.serenitymentalhealthsystem.dao.custom;

import lk.ijse.serenitymentalhealthsystem.dao.SuperDAO;
import lk.ijse.serenitymentalhealthsystem.entity.User;

public interface UserDAO extends SuperDAO {
    void save(User user);
    User findByUsername(String username);
}
