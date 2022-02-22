package ru.hogwarts.school_re.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school_re.model.Student;
import ru.hogwarts.school_re.service.StudentService;

import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
    this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity getStudent(@PathVariable long id) {
        Student getStudent = studentService.findStudent(id);
        if (getStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getStudent);
    }

    @GetMapping("/all")
    public ResponseEntity getAllStudentCollection() {
        return ResponseEntity.ok(studentService.getStudentCollection());
    }

    @GetMapping("/age={age}")
    public ResponseEntity filterStudentsByAge(@PathVariable int age) {
        Set<Student> filterStudentsByAge = Set.copyOf(studentService.filterStudentsByAge(age));
        if (filterStudentsByAge.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filterStudentsByAge);
    }

    @PutMapping()
    public ResponseEntity editStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        if (editStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeStudent(@PathVariable long id) {
        Student getStudent = studentService.findStudent(id);
        if (getStudent == null) {
            return ResponseEntity.notFound().build();
        }
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }
}
