package com.management.schoolmanagementv3.Service;

import com.management.schoolmanagementv3.Entity.Student;
import com.management.schoolmanagementv3.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<Student> findAll() {

        return studentRepository.findAll();
    }

    @Override
    public Student findByFirstName(String firstName) {

        return studentRepository.findByFirstName(firstName);
    }

    @Override
    public Student findByFirstNameAndLastName(String firstName, String lastName) {

        return studentRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public Optional<Student> findById(Integer id) {

        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {

        studentRepository.delete(student);
    }
}
