package com.management.schoolmanagementv3.Service;

import com.management.schoolmanagementv3.Entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    List<Student> findAll();

    Student findByFirstName(String firstName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Student> findById(Integer id);

    Student save(Student student);

    void delete(Student student);
}
