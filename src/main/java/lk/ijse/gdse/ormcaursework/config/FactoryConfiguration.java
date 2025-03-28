package lk.ijse.gdse.ormcaursework.config;


import lk.ijse.gdse.ormcaursework.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(User.class);


        sessionFactory = config.buildSessionFactory();
    }

    public static FactoryConfiguration getFactoryConfiguration() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
