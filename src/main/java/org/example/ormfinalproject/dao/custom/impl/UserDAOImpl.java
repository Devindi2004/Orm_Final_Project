package org.example.ormfinalproject.dao.custom.impl;

import org.example.ormfinalproject.Entity.Student;
import org.example.ormfinalproject.Entity.User;
import org.example.ormfinalproject.config.FactoryConfigaration;
import org.example.ormfinalproject.dao.custom.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<User> query = session.createQuery("FROM User ", User.class);
            users = (ArrayList<User>) query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return (users != null) ? users : new ArrayList<>();
    }

    @Override
    public boolean save(User userDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(userDTO);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User userDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.update(userDTO);
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
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        User user = session.get(User.class, id);
        if (user != null) {
            session.remove(user);
            tx.commit();
            session.close();
            return true;
        } else {
            tx.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        try {
            // Correct alias usage + ORDER BY
            Query<String> query = session.createQuery(
                    "SELECT c.id FROM User c ORDER BY c.id DESC",
                    String.class
            );
            query.setMaxResults(1);

            String lastId = query.uniqueResult();

            if (lastId == null) {
                return "S001";
            }

            int idNum = Integer.parseInt(lastId.replace("S", ""));
            idNum++;
            return String.format("S%03d", idNum);

        } finally {
            session.close();
        }
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
