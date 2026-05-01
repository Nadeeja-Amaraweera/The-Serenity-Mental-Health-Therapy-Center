package lk.ijse.serenitymentalhealthsystem.dao;

import lk.ijse.serenitymentalhealthsystem.dao.custom.impl.UserDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public <T extends SuperDAO> T getDAO (DAOTypes type){
        switch (type){
            case USER:
                return (T) new UserDAOImpl();

            default:
                return null;
        }
    }
}
