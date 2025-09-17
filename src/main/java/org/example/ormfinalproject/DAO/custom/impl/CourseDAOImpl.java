package org.example.ormfinalproject.DAO.custom.impl;

import org.example.ormfinalproject.DAO.custom.CourseDAO;
import org.example.ormfinalproject.Entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public ArrayList<Course> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Course customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Course customerDTO) throws SQLException, ClassNotFoundException {
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
    public Course search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
