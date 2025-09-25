package model.dao;

import model.entities.Patient;

import java.util.List;

public interface patientDao {

    void insert(Patient p);
    Patient findById(Integer id);
    Patient findByName(String name);
    List<Patient> findAll();
    void update(Integer id, String name, String email, Integer phone);
    void delete(Integer id);

}
