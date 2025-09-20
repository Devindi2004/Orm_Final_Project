package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.Entity.Instructor;
import org.example.ormfinalproject.config.FactoryConfigaration;
import org.example.ormfinalproject.dao.custom.CourseDAO;
import org.example.ormfinalproject.Entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {
    private Session session;

    @Override
    public ArrayList<Course> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Course> courses = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Course> query = session.createQuery("FROM Course ", Course.class);
            courses = (ArrayList<Course>) query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return (courses != null) ? courses : new ArrayList<>(); // ensure non-null
    }


    @Override
    public boolean save(Course courseDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(courseDTO);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course courseDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.update(courseDTO);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
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
