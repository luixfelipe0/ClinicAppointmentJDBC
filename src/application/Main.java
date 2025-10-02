package application;

import model.dao.DaoFactory;
import model.dao.DoctorDao;
import model.entities.Doctor;

public class Main {
    public static void main(String[] args) {

        DoctorDao doctorDao = DaoFactory.createDoctorDao();

//        System.out.println(doctorDao.findById(1));
//
        System.out.println(doctorDao.findBySpecialty("Cardiologia"));
//        System.out.println(doctorDao.findAll());

        doctorDao.insert(new Doctor(null, "Dr. Gabriel Lima", 123654, "Cardiologia", "21455821675", "gabriel.lima@clinic.com"));
    }
}