package model.dao;

import model.entities.Appointment;
import model.entities.Doctor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AppointmentDao {

    void schedule(Appointment a);
    void changeDoctor(Integer id, Integer newDoctorId);
    void changeDateTime(Integer id, LocalDateTime newDate);
    Appointment findById(Integer id);
    List<Appointment> findByPatient(Integer id);
    List<Appointment> findByDoctor(Integer id);
    List<Appointment> findByDate(LocalDate date);
    void cancelAppointment(Integer id);

}
