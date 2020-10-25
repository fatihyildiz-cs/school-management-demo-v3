package com.management.schoolmanagementv3.Controller;

import com.management.schoolmanagementv3.Entity.Teacher;
import com.management.schoolmanagementv3.Exception.ResourceNotFoundException;
import com.management.schoolmanagementv3.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/")
    public List<Teacher> getAllTeachers(){

        return teacherService.findAll();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Integer id){

        Optional<Teacher> foundTeacher = teacherService.findById(id);

        if(!foundTeacher.isPresent()){
            throw new ResourceNotFoundException("Teacher id: " + id);
        }
        return foundTeacher.get();
    }

    @PostMapping("/")
    public Teacher createTeacher(@RequestBody Teacher teacher){

        return teacherService.save(teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Integer id){

        Optional<Teacher> foundTeacher = teacherService.findById(id);

        if(!foundTeacher.isPresent()){
            throw new ResourceNotFoundException("Teacher id: " + id);
        }

        teacherService.delete(foundTeacher.get());
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher){

        Optional<Teacher> foundTeacher = teacherService.findById(id);
        if(!foundTeacher.isPresent()){
            throw new ResourceNotFoundException("Teacher id: " + id);
        }
        Teacher actualTeacher = foundTeacher.get();

        actualTeacher.setFirstName(teacher.getFirstName());
        actualTeacher.setLastName(teacher.getLastName());
        actualTeacher.setSubject(teacher.getSubject());
        return teacherService.save(actualTeacher);
    }
}
