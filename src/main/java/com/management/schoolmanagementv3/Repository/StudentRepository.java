package com.management.schoolmanagementv3.Repository;

import com.management.schoolmanagementv3.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByFirstName(String firstName);
    Student findByFirstNameAndLastName(String firstName, String lastName);
}
