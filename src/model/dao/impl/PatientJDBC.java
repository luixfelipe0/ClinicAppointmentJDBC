package model.dao.impl;

import db.DBConfig;
import db.DBException;
import model.dao.PatientDao;
import model.entities.Doctor;
import model.entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientJDBC implements PatientDao {

    private final Connection conn;

    public PatientJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Patient p) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO patients " +
                    "(name, birth_date, phone, email) " +
                    "VALUES " +
                    "(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, p.getName());
            st.setDate(2, new java.sql.Date(p.getBirthDate().getTime()));
            st.setString(3, p.getPhone());
            st.setString(4, p.getEmail());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    p.setId(id);
                }
                DBConfig.closeResultSet(rs);
            } else {
                throw new DBException("Something went wrong. No rows affected");
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
    public Patient findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM patients " +
                    "WHERE id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {

                Integer patientId = rs.getInt("id");
                String name = rs.getString("name");
                java.util.Date date = rs.getDate("birth_date");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                return new Patient(patientId, name, date, phone, email);
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
    public Patient findByName(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM patients " +
                    "WHERE name = ?");

            st.setString(1, name);

            rs = st.executeQuery();

            if (rs.next()) {

                Integer id = rs.getInt("id");
                String pName = rs.getString("name");
                java.util.Date date = rs.getDate("birth_date");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                return new Patient(id, pName, date, phone, email);
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
    public List<Patient> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;
        List<Patient> list = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM patients");

            rs = st.executeQuery();

            while (rs.next()) {

                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                java.util.Date date = rs.getDate("birth_date");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                list.add(new Patient(id, name, date, phone, email));
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
    public void update(Integer id, String name, String email, String phone) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE patients "
                            + "SET name = COALESCE(?, name), email = COALESCE(?, email), phone = COALESCE(?, phone) "
                            + "WHERE id = ?"
            );

            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, phone);
            st.setInt(4, id);

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
    public void delete(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM patients "
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
