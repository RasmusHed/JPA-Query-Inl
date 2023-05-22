package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(unique=true, nullable=false)
    private String enrollmentID;
    private String name;

    @Embedded
    private Address address;

   @Column(name="NUM_COURSES")
    private Integer numberOfCourses;
    public Student() {}

    public Student(String name)
    {
        this.name = name;
        this.numberOfCourses = 10;
    }

    public Student(String name, String enrollmentID, String street, String city,
                   String zipCode){
        this.name = name;
        this.enrollmentID= enrollmentID;
        this.address = new Address(street,city,zipCode);
    }

    public String toString() {
        return name + " lives at: " + this.address;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(enrollmentID, student.enrollmentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrollmentID);
    }
}
