package hibernate.demo;

import hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try(factory){
            Session session = factory.getCurrentSession();

            // start a transaction
            session.beginTransaction();

            // query the students
            List<Student> studentsList = session
                    .createQuery("from Student", Student.class)
                    .getResultList();

            // display the students
            System.out.println("List of all students:");
            studentsList.forEach(System.out::println);

            // query students by last name
            System.out.println("\nStudents queried by lastName:");
            studentsList = session
                    .createQuery("from Student s where s.lastName='Rutkowski'", Student.class)
                    .getResultList();

            // display query results
            studentsList.forEach(System.out::println);

            // query students by part of email
            System.out.println("\nStudents queried by email:");
            studentsList = session
                    .createQuery("from Student s where s.email like '%mail.com'", Student.class)
                    .getResultList();

            studentsList.forEach(System.out::println);

            // query with OR
            System.out.println("\nStudents with last name Rutkowski OR Florczak");
            studentsList = session
                    .createQuery("from Student s where s.lastName='Rutkowski' or s.lastName='Florczak'", Student.class)
                    .getResultList();

            studentsList.forEach(System.out::println);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }

    }
}
