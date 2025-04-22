package lk.ijse.gdse.ormcaursework.config;


import lk.ijse.gdse.ormcaursework.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Appointments.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Therapist.class);
        configuration.addAnnotatedClass(TPrograms.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(ProgramDetails.class);


        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
