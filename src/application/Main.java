package application;

import model.dao.DaoFactory;
import model.dao.DoctorDao;

public class Main {
    public static void main(String[] args) {

        DoctorDao doctorDao = DaoFactory.createDoctorDao();

        System.out.println(doctorDao.findById(1));

        System.out.println(doctorDao.findBySpecialty("Pediatria"));
        System.out.println(doctorDao.findAll());
    }
}