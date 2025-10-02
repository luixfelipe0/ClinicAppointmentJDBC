package model.dao.impl;

import db.DBConfig;
import db.DBException;
import model.dao.DoctorDao;
import model.entities.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorJDBC implements DoctorDao {

    private static Connection conn;

    public DoctorJDBC(Connection conn) {
        DoctorJDBC.conn = conn;
    }

    @Override
    public void insert(Doctor d) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO doctors " +
                    "(name, crm, specialty, email, phone) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, d.getName());
            st.setInt(2, d.getCrm());
            st.setString(3, d.getSpecialty());
            st.setString(4, d.getEmail());
            st.setString(5, d.getPhone());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                   int id = rs.getInt(1);
                   d.setId(id);
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
    public Doctor findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM doctors " +
                    "WHERE id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {

                Integer docId = rs.getInt("id");
                String name = rs.getString("name");
                Integer crm = rs.getInt("crm");
                String specialty = rs.getString("specialty");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                return new Doctor(docId, name, crm, specialty, phone, email);
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
    public List<Doctor> findBySpecialty(String specialty) {

        PreparedStatement st = null;
        ResultSet rs = null;
        List<Doctor> list = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM doctors " +
                    "WHERE specialty = ?");

            st.setString(1, specialty);

            rs = st.executeQuery();

            while (rs.next()) {

                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer crm = rs.getInt("crm");
                String docSpecialty = rs.getString("specialty");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                list.add(new Doctor(id, name, crm, docSpecialty, phone, email));
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
    public List<Doctor> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Doctor> list = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM doctors");

            rs = st.executeQuery();

            while (rs.next()) {

                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer crm = rs.getInt("crm");
                String specialty = rs.getString("specialty");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                list.add(new Doctor(id, name, crm, specialty, phone, email));
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
    public void update(Integer id, String specialty, String email, String phone) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE doctors "
                            + "SET specialty = COALESCE(?, specialty), email = COALESCE(?, email), phone = COALESCE(?, phone) "
                            + "WHERE id = ?"
            );

            st.setString(1, specialty);
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

    }
}
