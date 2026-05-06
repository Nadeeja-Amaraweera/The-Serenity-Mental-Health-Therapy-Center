package lk.ijse.serenitymentalhealthsystem.bo.custom;

import lk.ijse.serenitymentalhealthsystem.bo.SuperBO;
import lk.ijse.serenitymentalhealthsystem.dto.UserDTO;
import lk.ijse.serenitymentalhealthsystem.entity.Role;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO dto) throws Exception;
    UserDTO login(String username, String password) throws Exception;
}
