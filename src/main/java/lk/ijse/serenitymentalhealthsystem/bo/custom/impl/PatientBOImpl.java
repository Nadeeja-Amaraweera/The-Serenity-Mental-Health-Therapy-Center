package lk.ijse.serenitymentalhealthsystem.bo.custom.impl;

import lk.ijse.serenitymentalhealthsystem.bo.custom.PatientBO;
import lk.ijse.serenitymentalhealthsystem.dao.DAOFactory;
import lk.ijse.serenitymentalhealthsystem.dao.DAOTypes;
import lk.ijse.serenitymentalhealthsystem.dao.custom.PatientDAO;
import lk.ijse.serenitymentalhealthsystem.dao.custom.impl.PatientDAOImpl;

public class PatientBOImpl implements PatientBO {

    private final PatientDAO patientDAO = (PatientDAO)DAOFactory.getInstance().getDAO(DAOTypes.PATIENT);


}
