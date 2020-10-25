package com.management.schoolmanagementv3.IService;

import com.management.schoolmanagementv3.Entity.Student;
import com.management.schoolmanagementv3.Repository.StudentRepository;
import com.management.schoolmanagementv3.Service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getThreeStudents() {
        return null;
    }
}
