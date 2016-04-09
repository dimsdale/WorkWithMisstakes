package ua.utils;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateInit {
    private SessionFactory sessionFactory;

    public HibernateInit() {
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}