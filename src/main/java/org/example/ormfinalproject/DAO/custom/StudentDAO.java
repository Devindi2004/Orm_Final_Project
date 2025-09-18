package org.example.ormfinalproject.DAO.custom;

import org.example.ormfinalproject.DAO.CrudDAO;
import org.example.ormfinalproject.Entity.Student;

public interface StudentDAO extends CrudDAO<Student> {
    boolean delete(Long id);
}
