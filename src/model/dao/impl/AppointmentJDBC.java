package model.dao.impl;

import db.DBConfig;
import db.DBException;
import model.dao.AppointmentDao;
import model.dao.DaoFactory;
import model.dao.DoctorDao;
import model.dao.PatientDao;
import model.entities.Appointment;
import model.entities.Doctor;
import model.entities.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentJDBC implements AppointmentDao {

    private final Connection conn;
    private final DoctorDao docDao;
    private final PatientDao patDao;

    public AppointmentJDBC(Connection conn) {
        this.conn = conn;
        this.docDao = DaoFactory.createDoctorDao(conn);
        this.patDao = DaoFactory.createPatientDao(conn);
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
                throw new DBException("Something went wrong. No rows affected.");
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
    public void changeDoctor(Integer appointmentId, Integer newDoctorId) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE appointments "
                    + "SET doctor_id = ? "
                    + "WHERE id = ?"
            );

            if (docDao.findById(newDoctorId) == null || findById(appointmentId) == null) {
                throw new DBException("An error occurred, Doctor or appointment does not exist.");
            }

            st.setInt(1, newDoctorId);
            st.setInt(2, appointmentId);

            st.executeUpdate();

        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DBConfig.closeStatement(st);
        }
    }

    @Override
    public void changeDateTime(Integer id, LocalDateTime newDate) {
        PreparedStatement st = null;

        try {

            st = conn.prepareStatement(
                    "UPDATE appointments "
                    + "SET appointment_datetime = ? "
                    + "WHERE id = ?"
            );

            if (findById(id) == null) {
                throw new DBException("Appointment not found");
            }

            st.setTimestamp(1, Timestamp.valueOf(newDate));
            st.setInt(2, id);

            st.executeUpdate();

        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DBConfig.closeStatement(st);
        }

    }

    @Override
    public Appointment findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT a.*, p.name AS patient_name, d.name AS doctor_name " +
                    "FROM appointments a " +
                    "JOIN patients p ON patient_id = p.id " +
                    "JOIN doctors d ON doctor_id = d.id " +
                    "WHERE a.id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {

                Integer appointmentId = rs.getInt("id");
                Integer patientId = rs.getInt("patient_id");
                Integer doctorId = rs.getInt("doctor_id");
                String reason = rs.getString("reason");
                LocalDateTime dateTime = rs.getTimestamp("appointment_datetime").toLocalDateTime();

                Patient patient = patDao.findById(patientId);
                Doctor doctor = docDao.findById(doctorId);

                return new Appointment(appointmentId, patient, doctor, dateTime, reason);
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DBConfig.closeStatement(st);
            DBConfig.closeResultSet(rs);
        }

        return null;
    }

    @Override
    public List<Appointment> findByPatient(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        List<Appointment> list = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM appointments "
                    + "WHERE patient_id = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            while (rs.next()) {

                Integer appId = rs.getInt("id");
                Integer patientId = rs.getInt("patient_id");
                Integer docId = rs.getInt("doctor_id");
                String reason = rs.getString("reason");
                LocalDateTime dateTime = rs.getTimestamp("appointment_datetime").toLocalDateTime();

                Patient patient = patDao.findById(patientId);
                Doctor doctor = docDao.findById(docId);

                list.add(new Appointment(appId, patient, doctor, dateTime, reason));
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DBConfig.closeStatement(st);
            DBConfig.closeResultSet(rs);
        }
        return list;
    }

    @Override
    public List<Appointment> findByDoctor(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        List<Appointment> list = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM appointments "
                            + "WHERE doctor_id = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            while (rs.next()) {

                Integer appId = rs.getInt("id");
                Integer patientId = rs.getInt("patient_id");
                Integer docId = rs.getInt("doctor_id");
                String reason = rs.getString("reason");
                LocalDateTime dateTime = rs.getTimestamp("appointment_datetime").toLocalDateTime();

                Patient patient = patDao.findById(patientId);
                Doctor doctor = docDao.findById(docId);

                list.add(new Appointment(appId, patient, doctor, dateTime, reason));
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DBConfig.closeStatement(st);
            DBConfig.closeResultSet(rs);
        }
        return list;
    }

    @Override
    public List<Appointment> findByDate(LocalDate date) {

        PreparedStatement st = null;
        ResultSet rs = null;
        List<Appointment> list = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM appointments "
                    + "WHERE date(appointment_datetime) = ? "
            );

            st.setDate(1, java.sql.Date.valueOf(date));

            rs = st.executeQuery();


            while (rs.next()) {
                Patient patient = patDao.findById(rs.getInt("patient_id"));
                Doctor doctor = docDao.findById(rs.getInt("doctor_id"));

                list.add(new Appointment(
                        rs.getInt("id"),
                        patient,
                        doctor,
                        rs.getTimestamp("appointment_datetime").toLocalDateTime(),
                        rs.getString("reason")
                        ));
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DBConfig.closeStatement(st);
            DBConfig.closeResultSet(rs);
        }
        return list;
    }

    @Override
    public void cancelAppointment(Integer id) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM appointments "
                    + "WHERE id = ?"
            );

            st.setInt(1, id);
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DBConfig.closeStatement(st);
        }

    }
}
