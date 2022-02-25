package ru.hogwarts.school_re.controller;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school_re.model.Student;
import ru.hogwarts.school_re.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.FROM;
import static org.hibernate.loader.Loader.SELECT;

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
        if (studentService.filterStudentsByAge(age).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(studentService.filterStudentsByAge(age));
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

    @GetMapping()
    public ResponseEntity filterStudentBetweenAge(@RequestParam(required = true) int min,
                                                  @RequestParam(required = true) int max) {
        return ResponseEntity.ok(studentService.findStudentsByAgeBetween(min, max));
    }
}
