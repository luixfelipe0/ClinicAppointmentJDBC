package model.dao.impl;

import model.dao.doctorDao;
import model.entities.Doctor;

import java.util.List;

public class DoctorJDBC implements doctorDao {

    @Override
    public void insert(Doctor d) {

    }

    @Override
    public Doctor findById(Integer id) {
        return null;
    }

    @Override
    public Doctor findBySpecialty(String specialty) {
        return null;
    }

    @Override
    public List<Doctor> findAll() {
        return List.of();
    }

    @Override
    public void update(Integer id, String specialty, String email, Integer phone) {

    }

    @Override
    public void delete(Integer id) {

    }
}
