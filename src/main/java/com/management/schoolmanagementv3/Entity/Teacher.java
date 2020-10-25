package com.management.schoolmanagementv3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    //I see bad request code but cannot get a response. Why?
    @Enumerated(EnumType.STRING)
    private SubjectType subject;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "teacherSet")
    @JsonIgnore
    private Set<SchoolClass> schoolClassSet = new HashSet<>();

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    public Set<SchoolClass> getSchoolClassSet() {
        return schoolClassSet;
    }

    public void setSchoolClassSet(Set<SchoolClass> schoolClassSet) {
        this.schoolClassSet = schoolClassSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SubjectType getSubject() {
        return subject;
    }

    public void setSubject(SubjectType subject) {
        this.subject = subject;
    }

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, SubjectType subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }
}
