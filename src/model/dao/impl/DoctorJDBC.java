package model.dao.impl;

import db.DBConfig;
import db.DBException;
import model.dao.DoctorDao;
import model.entities.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DoctorJDBC implements DoctorDao {

    private static Connection conn;

    public DoctorJDBC(Connection conn) {
        DoctorJDBC.conn = conn;
    }

    @Override
    public void insert(Doctor d) {

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
    public Doctor findBySpecialty(String specialty) {
        return null;
    }

    @Override
    public List<Doctor> findAll() {
        return List.of();
    }

    @Override
    public void update(Integer id, String specialty, String email, Integer phone) {

    }

    @Override
    public void delete(Integer id) {

    }
}
