package model.dao;

import db.DBConfig;
import model.dao.impl.AppointmentJDBC;
import model.dao.impl.DoctorJDBC;
import model.dao.impl.PatientJDBC;

import java.sql.Connection;

public class DaoFactory {

    public static AppointmentDao createAppointmentDao(Connection conn) {
        return new AppointmentJDBC(conn);
    }

    public static DoctorDao createDoctorDao(Connection conn) {
        return new DoctorJDBC(conn);
    }

    public static PatientDao createPatientDao(Connection conn) {
        return new PatientJDBC(conn);
    }

}
