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
    public <T extends SuperDAO> T getDAO (DAOTypes daoType){
       return switch (daoType){
            case USER ->(T) new UserDAOImpl();

       };
    }
}
