package com.management.schoolmanagementv3.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SchoolClass {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer year;
    @BranchCode //This custom annotation allows only 1 digit uppercase char's.
    private String branch;
    private Integer studentCount = 0;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "class_id",referencedColumnName = "id")
    @OneToMany(mappedBy="schoolClass", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();



    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_classes",
        joinColumns = {@JoinColumn(name = "class_id")}, inverseJoinColumns = {@JoinColumn(name = "teacher_id")}
        )
    private Set<Teacher> teacherSet = new HashSet<>();

    public SchoolClass() {
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public SchoolClass(Integer year, String branch) {
        this.year = year;
        this.branch = branch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", year=" + year +
                ", branch='" + branch + '\'' +
                ", studentCount=" + studentCount +
                '}';
    }
}
