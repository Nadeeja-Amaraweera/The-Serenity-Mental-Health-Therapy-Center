package lk.ijse.serenitymentalhealthsystem.bo.custom.impl;

import lk.ijse.serenitymentalhealthsystem.bo.custom.UserBO;
import lk.ijse.serenitymentalhealthsystem.dao.DAOFactory;
import lk.ijse.serenitymentalhealthsystem.dao.DAOTypes;
import lk.ijse.serenitymentalhealthsystem.dao.custom.UserDAO;
import lk.ijse.serenitymentalhealthsystem.dto.UserDTO;
import lk.ijse.serenitymentalhealthsystem.entity.User;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO =  (UserDAO) DAOFactory.getInstance().getDAO(DAOTypes.USER);


    @Override
    public boolean saveUser(UserDTO dto) throws Exception {
        User newUser = new User();
        newUser.setId(dto.getUserId());
        newUser.setUsername(dto.getUserName());
        newUser.setPassword(dto.getPassword());
        newUser.setRole(dto.getRole());

        return userDAO.save(newUser);
    }
}