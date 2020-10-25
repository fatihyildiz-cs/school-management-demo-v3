package com.management.schoolmanagementv3.Service;

import com.management.schoolmanagementv3.Entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeacherService {

    List<Teacher> findAll();

    Optional<Teacher> findById(Integer id);

    Teacher save(Teacher teacher);

    void delete(Teacher teacher);
}
