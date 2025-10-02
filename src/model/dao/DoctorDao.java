package model.dao;

import model.entities.Doctor;

import java.util.List;

public interface DoctorDao {

    void insert(Doctor d);
    Doctor findById(Integer id);
    List<Doctor> findBySpecialty(String specialty);
    List<Doctor> findAll();
    void update(Integer id, String specialty, String email, Integer phone);
    void delete(Integer id);

}
