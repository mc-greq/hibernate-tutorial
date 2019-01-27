package hibernate.demo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        // create factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try(factory){
            // get session and start transaction
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            int studentId = 4;
            System.out.println("\nGetting student with id: " + studentId);

            Student tempStudent = session.get(Student.class, studentId);

            //delete the student
            System.out.println("Deleting student:" + tempStudent);
            if(tempStudent != null) {
                session.delete(tempStudent);
            }

            // delete student id = 2
            System.out.println("Deleting student id = 2");
            session.createQuery("delete from Student where id=2")
                    .executeUpdate();

            // commit changes
            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
