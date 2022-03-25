package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class StudentService implements StudentDAO {

    @Override
    public List<Student> getAllStudents() {

        //try/catch/finally is making sure the session is closed

        Session closeSession = null;

        try {
            SessionFactory sessions = new Configuration().configure().buildSessionFactory();
            Session session = sessions.openSession();
            TypedQuery<Student> typedQuery = session.createQuery("FROM Student");
            List<Student> students = typedQuery.getResultList();
            session.close();
            sessions.close();
            return students;
        } finally {
            if (closeSession != null) closeSession.close();
        }
    }

    @Override
    public Student getStudentByEmail(String sEmail) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Student student = session.get(Student.class, sEmail);
        factory.close();
        session.close();
        return student;
    }

    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
        List<Student> valStudent = getAllStudents();
        for (Student  student: valStudent) {
            if(Objects.equals(student.getsEmail(), sEmail) && Objects.equals(student.getsPass(), sPassword)) {
                return true;
            }
        }
        return false;
    }



    @Override
    public void registerStudentToCourse(String sEmail, int cId) {
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();

        Student student = getStudentByEmail(sEmail);
        for(Course c : student.getsCourses()) {
            if (c.getcId() == cId) {
                return;
            }
        }
        CourseService courseService = new CourseService();
        List<Course> courses = courseService.getAllCourses();
        for(Course c : courses) {
            if(c.getcId() == cId) {
                student.getsCourses().add(c);
                Session session = sessions.openSession();
                Transaction transaction = session.beginTransaction();
                session.merge(student);
                transaction.commit();
                session.close();
                sessions.close();
            }
         }
    }

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        return getStudentByEmail(sEmail).getsCourses();
    }
}
