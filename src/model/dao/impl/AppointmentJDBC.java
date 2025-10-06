package model.dao.impl;

import db.DBConfig;
import db.DBException;
import model.dao.AppointmentDao;
import model.entities.Appointment;
import model.entities.Doctor;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class AppointmentJDBC implements AppointmentDao {

    private static Connection conn;

    public AppointmentJDBC(Connection conn) {
        AppointmentJDBC.conn = conn;
    }

    @Override
    public void schedule(Appointment a) {

        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "INSERT INTO appointments "
                    + "(patient_id, doctor_id, appointment_datetime, reason) "
                    + "VALUES "
                    + "(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, a.getPatient().getId());
            st.setInt(2, a.getDoctor().getId());
            st.setTimestamp(3, Timestamp.valueOf(a.getDateTime()));
            st.setString(4, a.getReason());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    a.setId(id);
                }
                DBConfig.closeResultSet(rs);
            } else {
                throw new DBException("Sometihng went wrong. No rows affected.");
            }

        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DBConfig.closeStatement(st);
        }

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
