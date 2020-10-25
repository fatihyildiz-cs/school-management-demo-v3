package com.management.schoolmanagementv3.Controller;

import com.management.schoolmanagementv3.Entity.SchoolClass;
import com.management.schoolmanagementv3.Entity.Student;
import com.management.schoolmanagementv3.Entity.Teacher;
import com.management.schoolmanagementv3.Exception.ResourceAlreadyExistsException;
import com.management.schoolmanagementv3.Exception.ResourceNotFoundException;
import com.management.schoolmanagementv3.Service.SchoolClassService;
import com.management.schoolmanagementv3.Service.StudentService;
import com.management.schoolmanagementv3.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v3/classes")
public class SchoolClassController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SchoolClassService schoolClassService;

    @GetMapping("/")
    public List<SchoolClass> getAllClasses(){

        return schoolClassService.findAll();
    }

    @GetMapping("/{id}")
    public SchoolClass getClassById(@PathVariable Integer id){

        Optional<SchoolClass> foundClass = schoolClassService.findById(id);
        if(!foundClass.isPresent()){
            throw new ResourceNotFoundException("Class id: " + id);
        }

        return foundClass.get();
    }

    @PostMapping("/")
    public SchoolClass createClass(@RequestBody SchoolClass schoolClass){

        return schoolClassService.save(schoolClass);
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Integer id){

        Optional<SchoolClass> foundClass = schoolClassService.findById(id);
        if(!foundClass.isPresent()){
            throw new ResourceNotFoundException("Class id: " + id);
        }
        schoolClassService.delete(foundClass.get());
    }

    @PutMapping("/{id}")
    public SchoolClass updateClass(@PathVariable Integer id, @RequestBody SchoolClass schoolClass){

        Optional<SchoolClass> foundClass = schoolClassService.findById(id);
        if(!foundClass.isPresent()){
            throw new ResourceNotFoundException("Class id: " + id);
        }
        SchoolClass actualClass  = foundClass.get();

        actualClass.setBranch(schoolClass.getBranch());
        actualClass.setStudentCount(schoolClass.getStudentCount());
        actualClass.setYear(schoolClass.getYear());

        return schoolClassService.save(actualClass);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentsOfClass(@PathVariable Integer id){

        Optional<SchoolClass> foundClass = schoolClassService.findById(id);
        if(!foundClass.isPresent()){
            throw new ResourceNotFoundException("Class id: " + id);
        }

        return foundClass.get().getStudents();
    }

    @GetMapping("/{id}/teachers")
    public Set<Teacher> getTeachersOfClass(@PathVariable Integer id){

        Optional<SchoolClass> foundClass = schoolClassService.findById(id);
        if(!foundClass.isPresent()){
            throw new ResourceNotFoundException("Class id: " + id);
        }
        SchoolClass actualClass = foundClass.get();

        return actualClass.getTeacherSet();
    }

    //I manually update the student count here. There should be a better way?
    //Which one is better? if or try-catch?
    @PostMapping("/{id}/students/{studentId}")
    public void registerStudentToClass(@PathVariable Integer id, @PathVariable Integer studentId){

        Optional<SchoolClass> foundClass = schoolClassService.findById(id);
        if(!foundClass.isPresent()){
            throw new ResourceNotFoundException("Class id: " + id);
        }
        SchoolClass actualClass = foundClass.get();

        Optional<Student> foundStudent = studentService.findById(studentId);
        if(!foundStudent.isPresent()){
            throw new ResourceNotFoundException("Student id: " + studentId);
        }
        Student actualStudent = foundStudent.get();

        if(isStudentAlreadyInClass(id, studentId)){
            throw new ResourceAlreadyExistsException("Student with id "+ studentId+ " already exists in class with id "+id);
        }

        actualStudent.setSchoolClass(actualClass);
        studentService.save(actualStudent);

        // update student count
        actualClass.setStudentCount(actualClass.getStudents().size());
        schoolClassService.save(actualClass);
    }
//    @PostMapping("/{id}/students/{studentId}")
//    public void registerStudentToClass(@PathVariable Integer id, @PathVariable Integer studentId){
//
//        SchoolClass actualClass = new SchoolClass();
//        Student actualStudent = new Student();
//
//        try{
//             actualClass = schoolClassRepository.findById(id).get();
//             actualStudent = studentRepository.findById(studentId).get();
//        }
//        catch (ResourceNotFoundException exception){
//            throw new ResourceNotFoundException("Either class with id "+id + "or student with id "+ studentId+ "not found.");
//        }
//
//        actualStudent.setSchoolClass(actualClass);
//        studentRepository.save(actualStudent);
//
//        // update student count
//        actualClass.setStudentCount(actualClass.getStudents().size());
//        schoolClassRepository.save(actualClass);
//    }

    @GetMapping("/{id}/students/{studentId}")

    public boolean isStudentAlreadyInClass(@PathVariable Integer id, @PathVariable Integer studentId){

        Optional<SchoolClass> foundClass = schoolClassService.findById(id);
        if(!foundClass.isPresent()){
            throw new ResourceNotFoundException("Class id: " + id);
        }
        SchoolClass actualClass = foundClass.get();

        for (Student student : actualClass.getStudents()) {
            if(student.getId() == studentId){
                return true;
            }
        }
        return false;
    }

    @PostMapping("/{id}/teachers/{teacherId}")
    public Teacher registerTeacherToClass(@PathVariable Integer id, @PathVariable Integer teacherId){

        Optional<SchoolClass> foundClass = schoolClassService.findById(id);
        if(!foundClass.isPresent()){
            throw new ResourceNotFoundException("Class id: " + id);
        }
        SchoolClass actualClass = foundClass.get();

        Optional<Teacher> foundTeacher = teacherService.findById(teacherId);
        if(!foundTeacher.isPresent()){
            throw new ResourceNotFoundException("Teacher id: " + teacherId);
        }
        Teacher actualTeacher = foundTeacher.get();

        actualClass.getTeacherSet().add(actualTeacher);
        actualTeacher.getSchoolClassSet().add(actualClass);

        schoolClassService.save(actualClass);
        teacherService.save(actualTeacher);

        return actualTeacher;
    }


}
