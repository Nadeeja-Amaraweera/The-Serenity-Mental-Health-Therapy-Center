package lk.ijse.serenitymentalhealthsystem.config;

import lk.ijse.serenitymentalhealthsystem.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ?
                factoryConfiguration = new FactoryConfiguration() :
                factoryConfiguration;
    }

    // new session
    public Session getSession() {
        return sessionFactory.openSession();
    }

    // return same object
    // thread bound session
    // auto close -> commit , rollback
    // recommend for layered dao + (service) bo architecture
    //     <property name="hibernate.current_session_context_class">thread</property>
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
