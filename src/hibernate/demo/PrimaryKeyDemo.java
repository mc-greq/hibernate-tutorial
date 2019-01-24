package hibernate.demo;

import hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try{
            // create 3 student objects
            System.out.println("Creating 3 student objects");
            Student student1 = new Student("Marta", "Florczak", "mf@gmail.com");
            Student student2 = new Student("Andrzej", "Kowalski", "ak@gmail.com");
            Student student3 = new Student("Mosze", "Rosenbaum", "mr@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the students...");
            session.save(student1);
            session.save(student2);
            session.save(student3);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Transaction committed!");

        } finally {
            factory.close();
        }
    }
}
