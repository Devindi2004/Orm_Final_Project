package org.example.ormfinalproject.config;

import org.example.ormfinalproject.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfigaration {
    private static FactoryConfigaration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfigaration() {
        // Hibernate automatically looks for hibernate.properties in classpath (src/main/resources)
        Configuration configuration = new Configuration();

        // Register annotated entity classes
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Lesson.class);

        // Build the session factory
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfigaration getInstance() {
        return (factoryConfiguration == null)
                ? factoryConfiguration = new FactoryConfigaration()
                : factoryConfiguration;
    }

    // ✅ Method name was confusing before – this one now correctly returns SessionFactory
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // ✅ Use this when you want a Hibernate session
    public Session getSession() {
        return sessionFactory.openSession();
    }

    // Note:
    // - SessionFactory is thread-safe (singleton)
    // - Session is NOT thread-safe (use per transaction/request)
}
