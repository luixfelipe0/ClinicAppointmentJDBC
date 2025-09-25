package model.dao;

import model.dao.impl.AppointmentJDBC;
import model.dao.impl.DoctorJDBC;
import model.dao.impl.PatientJDBC;

public class DaoFactory {

    public static appointmentDao createAppointmentDao() {
        return new AppointmentJDBC();
    }

    public static doctorDao createDoctorDao() {
        return new DoctorJDBC();
    }

    public static patientDao createPatientDao() {
        return new PatientJDBC();
    }

}
