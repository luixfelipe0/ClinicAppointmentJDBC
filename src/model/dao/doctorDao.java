package model.dao;

import model.entities.Doctor;
import model.entities.Patient;

import java.util.List;

public interface doctorDao {

    void insert(Doctor d);
    Doctor findById(Integer id);
    Doctor findBySpecialty(String specialty);
    List<Doctor> findAll();
    void update(Integer id, String specialty, String email, Integer phone);
    void delete(Integer id);

}
