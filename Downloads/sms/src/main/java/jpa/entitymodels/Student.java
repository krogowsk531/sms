package jpa.entitymodels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Student")

public class Student {

    @Id
    @Column(name="email", length = 50, nullable = false)
    private String sEmail;
    @Column(name="name", length = 50, nullable = false)
    private String sName;
    @Column(name="password", length = 50, nullable = false)
    private String sPass;
    @OneToMany(targetEntity = Course.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Course> sCourses;

    public Student() {
        this.sEmail = "";
        this.sName = "";
        this.sPass = "";
        this.sCourses = new ArrayList<>();
    }

    public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return sEmail.equals(student.sEmail) && sName.equals(student.sName) && sPass.equals(student.sPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sEmail, sName, sPass, sCourses);
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }
}
