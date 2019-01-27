package hibernate.demo;

import hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        // create factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try(factory){
            // create session
            Session session = factory.getCurrentSession();

            // start a transaction
            session.beginTransaction();

            Student tempStudent = session.get(Student.class, 5);

            // set retrieved students name, wont be saved in DB until commit
            System.out.println("Updating student...");
            tempStudent.setFirstName("Glupek");

            // commit transaction
            session.getTransaction().commit();

            // begin another transaction to do some more updates
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Update emails for all students...");
            session.createQuery("update Student set email='new@mail.com'")
                    .executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
