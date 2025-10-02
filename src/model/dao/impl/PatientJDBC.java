package model.dao.impl;

import model.dao.PatientDao;
import model.entities.Patient;

import java.util.List;

public class PatientJDBC implements PatientDao {

    @Override
    public void insert(Patient p) {

    }

    @Override
    public Patient findById(Integer id) {
        return null;
    }

    @Override
    public Patient findByName(String name) {
        return null;
    }

    @Override
    public List<Patient> findAll() {
        return List.of();
    }

    @Override
    public void update(Integer id, String name, String email, Integer phone) {

    }

    @Override
    public void delete(Integer id) {

    }
}
