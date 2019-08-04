package listeners;


import entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;
import java.util.logging.Level;

public class HibernateInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            Configuration configuration = new Configuration();

            Properties hbnProperties = new Properties();
            hbnProperties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            hbnProperties.put(Environment.URL, "jdbc:mysql://localhost:3306/skills_collector?useSSL=false&serverTimezone=UTC");
            // Nazwę użytkownika dostosuj do swojej instalacji MySQL
            hbnProperties.put(Environment.USER, "root");
            // Hasło użytkownika dostosuj do swojej instalacji MySQL
            hbnProperties.put(Environment.PASS, "asd123");
            hbnProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            hbnProperties.put(Environment.SHOW_SQL, "true");
            hbnProperties.put(Environment.FORMAT_SQL, "true");
            hbnProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            // W przypadku gdy silnik Hibernate ma tworzyć schemat bazy danych, to poniżej
            // użyj opcji create-drop albo update
            hbnProperties.put(Environment.HBM2DDL_AUTO, "validate");
            configuration.setProperties(hbnProperties);

            // Odkomentuj poniższe instrukcje po utworzeniu klas encji (kolejne zadania)

            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Source.class);
            configuration.addAnnotatedClass(Skill.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sce.getServletContext().setAttribute("session_factory", sessionFactory);
        } catch (Exception e) {
            ;
           // logger.log(Level.SEVERE, "Błąd konfiguracji Hibernate!", e);
        }
    }
}
