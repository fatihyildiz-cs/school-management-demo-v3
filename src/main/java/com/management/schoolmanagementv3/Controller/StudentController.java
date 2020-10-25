package com.management.schoolmanagementv3.Controller;

import com.management.schoolmanagementv3.Entity.Student;
import com.management.schoolmanagementv3.Exception.ResourceNotFoundException;
import com.management.schoolmanagementv3.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public List<Student> getAllStudents(){

        return studentService.findAll();
    }

    @GetMapping("/findbyfirstname/{firstName}")
    public Student getAllStudents(@PathVariable String firstName){

        return studentService.findByFirstName(firstName);
    }

    @GetMapping("/findbyfirstnameandlastname/{firstName}/{lastName}")
    public Student getAllStudents(@PathVariable String firstName, @PathVariable String lastName){

        return studentService.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id){

        Optional<Student> foundStudent = studentService.findById(id);

        if(!foundStudent.isPresent()){
            throw new ResourceNotFoundException("Student id: "+ id);
        }
        return foundStudent.get();
    }

    @PostMapping("/")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student){

        Student savedStudent = studentService.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id){

        Optional<Student> foundStudent = studentService.findById(id);
        if(!foundStudent.isPresent()){
            throw new ResourceNotFoundException("Student id: " + id);
        }
        studentService.delete(foundStudent.get());
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student){

        Optional<Student> foundStudent = studentService.findById(id);
        if(!foundStudent.isPresent()){
            throw new ResourceNotFoundException("Student id: " + id);
        }

        Student actualStudent = foundStudent.get();

        actualStudent.setFirstName(student.getFirstName());
        actualStudent.setLastName(student.getLastName());

        return studentService.save(actualStudent);
    }




}
