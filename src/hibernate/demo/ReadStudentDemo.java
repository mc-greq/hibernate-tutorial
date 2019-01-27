package hibernate.demo;

import hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();;

        try(factory){
            // create session
            Session session = factory.getCurrentSession();

            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Training", "Student", "retreive@mail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            // retrieving saved object
            System.out.println("Saved student generated id: " + tempStudent.getId());

            // get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());

            Student retrievedStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Get complete: " + retrievedStudent);

            // commit the transaction
            session.getTransaction().commit();



            System.out.println("Done!");
        }
    }
}
