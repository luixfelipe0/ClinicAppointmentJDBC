package model.dao;

import model.entities.Appointment;
import model.entities.Doctor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface appointmentDao {

    void schedule(Appointment a);
    void changeDoctor(Integer id, Doctor newDoctor);
    void changeDate(Integer id, Date newDate);
    void changeTime(Integer id, LocalDateTime newTime);
    Appointment findById(Integer id);
    List<Appointment> findByPatient(Integer id);
    List<Appointment> findByDoctor(Integer id);
    List<Appointment> findByDate(Date date);
    void cancelAppointment(Integer id);

}
