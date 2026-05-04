package lk.ijse.serenitymentalhealthsystem.bo.custom;

import lk.ijse.serenitymentalhealthsystem.bo.SuperBO;
import lk.ijse.serenitymentalhealthsystem.dto.UserDTO;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO dto) throws Exception;
}
