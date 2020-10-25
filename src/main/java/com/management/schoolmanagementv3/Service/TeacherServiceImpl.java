package com.management.schoolmanagementv3.Service;

import com.management.schoolmanagementv3.Entity.Teacher;
import com.management.schoolmanagementv3.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll() {

        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Integer id) {

        return teacherRepository.findById(id);
    }

    @Override
    public Teacher save(Teacher teacher) {

        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Teacher teacher) {

        teacherRepository.delete(teacher);
    }
}
