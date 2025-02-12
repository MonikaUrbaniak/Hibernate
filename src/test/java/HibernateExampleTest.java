
import jakarta.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;

import jakarta.persistence.EntityManagerFactory;
import org.example.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class HibernateExampleTest {
    private EntityManagerFactory sessionFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
    @AfterEach
    protected void tearDown() throws Exception {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Test
    void hql_feth_users(){
 EntityManager session = sessionFactory.createEntityManager();
            session.getTransaction().begin();
            List<User> users = session.createQuery("select u from User u",User.class).getResultList();
            users.forEach(System.out::println);
            session.getTransaction().commit();
            session.close();
        }

    @Test
    void save_my_object_to_db(){
        User user = new User("Monika", LocalDate.of(1994, Month.JUNE,6));

        try (EntityManager session = sessionFactory.createEntityManager()) {
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        }

    }


//    @Test
//    @Disabled
//
//    public void how_does_hibernate_work(){
//        User user = null;
//        assertThat(user.getName()).isEqualTo("Marco");
//    }
}
