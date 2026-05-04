package lk.ijse.serenitymentalhealthsystem.bo;

import lk.ijse.serenitymentalhealthsystem.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){};

    public static BOFactory getInstance(){
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    @SuppressWarnings("unchecked")
    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        return switch (boTypes) {
            case USER -> (T) new UserBOImpl();
        };
    }
}
