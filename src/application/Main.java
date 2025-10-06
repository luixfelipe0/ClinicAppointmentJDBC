package application;

import db.DBConfig;
import model.dao.AppointmentDao;
import model.dao.DaoFactory;
import model.dao.DoctorDao;
import model.dao.PatientDao;
import model.entities.Appointment;
import model.entities.Doctor;
import model.entities.Patient;

import java.sql.Connection;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Connection conn = DBConfig.getConn();

        DoctorDao doctorDao = DaoFactory.createDoctorDao(conn);
        PatientDao patientDao = DaoFactory.createPatientDao(conn);
        AppointmentDao appointmentDao = DaoFactory.createAppointmentDao(conn);

//        System.out.println(doctorDao.findById(1));
//
//        System.out.println(doctorDao.findBySpecialty("Cardiologia"));
//
//        System.out.println(doctorDao.findAll());
//
//        doctorDao.insert(new Doctor(null, "Dr. Gabriel Lima", 123654, "Cardiologia", "21455821675", "gabriel.lima@clinic.com"));
//
//        doctorDao.update(5, "Endocrinologia", null, null);
//
//        doctorDao.delete(7);

//        patientDao.insert(new Patient(null, "Samara Souza", new Date(), "51792316558", "samara.souza@email.com"));

//        System.out.println(patientDao.findById(2));
//
//        System.out.println(patientDao.findByName("Fernanda Rocha"));
//
//        System.out.println(patientDao.findAll());
//
//        patientDao.update(1, "Lucas Souza", "lucas.souza@email.com", null);

//        patientDao.delete(6);

//    appointmentDao.schedule(new Appointment(null, patientDao.findById(1), doctorDao.findById(1), LocalDateTime.of(2025, 10, 5, 9, 30), "Dor na lombar"));

        System.out.println(appointmentDao.findById(9));

    }
}