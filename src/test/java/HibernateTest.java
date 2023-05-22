import jakarta.persistence.*;
import se.yrgo.domain.Student;
import se.yrgo.domain.Subject;
import se.yrgo.domain.Tutor;

import java.util.List;

public class HibernateTest {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

    public static void main(String[] args) {
        setUpData();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

//        Task 1!
//        Subject science = em.find(Subject.class, 2);
//        Query query = em.createQuery("select tutor.teachingGroup from Tutor tutor where :subject member of tutor.subjectsToTeach");
//        query.setParameter("subject", science);
//        List<Student> students = query.getResultList();
//        for (Student student : students) {
//            System.out.println(student);
//        }

//        Task 2!
//        List<Object[]> studentAndTutorName = em.createQuery("select student.name, tutor.name from Tutor tutor join tutor.teachingGroup student").getResultList();
//        for (Object[] obj:studentAndTutorName) {
//            System.out.println("Student name: " + obj[0] + " Tutor name: " + obj[1]);
//        }

//        Task 3!
//        Double averageSemester = (Double) em.createQuery("select avg(subject.numberOfSemesters) from Subject subject").getSingleResult();
//        System.out.println("The average semester is: " + averageSemester);
//
//        Task 4!
//        Object query = em.createNativeQuery("select max(tutor.salary) from Tutor tutor").getSingleResult();
//        System.out.println(query);

//        Task 5!
//        List<Tutor> query = em.createNamedQuery("salaryOverTenK", Tutor.class).getResultList();
//        for (Tutor tutor : query) {
//            System.out.println(tutor);
//        }
        tx.commit();
        em.close();
    }

    public static void setUpData() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Subject mathematics = new Subject("Mathematics", 2);
        Subject science = new Subject("Science", 2);
        Subject programming = new Subject("Programming", 3);
        em.persist(mathematics);
        em.persist(science);
        em.persist(programming);

        Tutor t1 = new Tutor("ABC123", "Johan Smith", 40000);
        t1.addSubjectsToTeach(mathematics);
        t1.addSubjectsToTeach(science);


        Tutor t2 = new Tutor("DEF456", "Sara Svensson", 20000);
        t2.addSubjectsToTeach(mathematics);
        t2.addSubjectsToTeach(science);

        Tutor t3 = new Tutor("GHI678", "Karin Lindberg", 0);
        t3.addSubjectsToTeach(programming);

        t1.createStudentAndAddtoTeachingGroup("Jimi Hendriks", "1-HEN-2019", "Street 1", "city 2", "1212");
        t1.createStudentAndAddtoTeachingGroup("Bruce Lee", "2-LEE-2019", "Street 2", "city 2", "2323");
        t3.createStudentAndAddtoTeachingGroup("Roger Waters", "3-WAT-2018", "Street 3", "city 3", "34343");

        em.persist(t1);
        em.persist(t2);
        em.persist(t3);

        tx.commit();
        em.close();
    }
}
