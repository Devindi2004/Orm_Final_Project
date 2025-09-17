package org.example.ormfinalproject.DAO.custom.impl;

import org.example.ormfinalproject.DAO.custom.InstructorDAO;
import org.example.ormfinalproject.Entity.Instructor;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorDAOImpl implements InstructorDAO {
    @Override
    public ArrayList<Instructor> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Instructor customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Instructor customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Instructor search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
