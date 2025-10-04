package application;

import model.dao.DaoFactory;
import model.dao.DoctorDao;
import model.dao.PatientDao;
import model.entities.Doctor;
import model.entities.Patient;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        DoctorDao doctorDao = DaoFactory.createDoctorDao();
        PatientDao patientDao = DaoFactory.createPatientDao();

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

        System.out.println(patientDao.findById(2));

        System.out.println(patientDao.findByName("Fernanda Rocha"));

        System.out.println(patientDao.findAll());

        patientDao.update(1, "Lucas Souza", "lucas.souza@email.com", null);

        patientDao.delete(6);
        patientDao.delete(7);
        patientDao.delete(8);
        patientDao.delete(9);
        patientDao.delete(10);

    }
}