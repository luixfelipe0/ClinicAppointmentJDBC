package model.dao.impl;

import model.dao.AppointmentDao;
import model.entities.Appointment;
import model.entities.Doctor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class AppointmentJDBC implements AppointmentDao {

    @Override
    public void schedule(Appointment a) {

    }

    @Override
    public void changeDoctor(Integer id, Doctor newDoctor) {

    }

    @Override
    public void changeDate(Integer id, Date newDate) {

    }

    @Override
    public void changeTime(Integer id, LocalDateTime newTime) {

    }

    @Override
    public Appointment findById(Integer id) {
        return null;
    }

    @Override
    public List<Appointment> findByPatient(Integer id) {
        return List.of();
    }

    @Override
    public List<Appointment> findByDoctor(Integer id) {
        return List.of();
    }

    @Override
    public List<Appointment> findByDate(Date date) {
        return List.of();
    }

    @Override
    public void cancelAppointment(Integer id) {

    }
}
