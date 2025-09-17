package org.example.ormfinalproject.DAO.custom.impl;

import org.example.ormfinalproject.DAO.custom.StudentDAO;
import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.config.FactoryConfigaration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;


public class StudentDAOImpl implements StudentDAO {
    private Session session;

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
    return null;
    }

    @Override
    public boolean save(Student customerDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(customerDTO);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student customerDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(customerDTO);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.delete(id);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        try {
            // Correct alias usage + ORDER BY
            Query<String> query = session.createQuery(
                    "SELECT c.id FROM Student c ORDER BY c.id DESC",
                    String.class
            );
            query.setMaxResults(1);

            String lastId = query.uniqueResult();

            // If no records yet, return first ID
            if (lastId == null) {
                return "S001";
            }

            // Otherwise, increment the numeric part of the ID
            int idNum = Integer.parseInt(lastId.replace("S", ""));
            idNum++;
            return String.format("S%03d", idNum);

        } finally {
            session.close();
        }
    }


    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
