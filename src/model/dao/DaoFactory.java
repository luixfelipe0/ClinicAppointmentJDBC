package model.dao;

import db.DBConfig;
import model.dao.impl.AppointmentJDBC;
import model.dao.impl.DoctorJDBC;
import model.dao.impl.PatientJDBC;

public class DaoFactory {

    public static AppointmentDao createAppointmentDao() {
        return new AppointmentJDBC();
    }

    public static DoctorDao createDoctorDao() {
        return new DoctorJDBC(DBConfig.getConn());
    }

    public static PatientDao createPatientDao() {
        return new PatientJDBC(DBConfig.getConn());
    }

}
