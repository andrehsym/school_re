package ru.hogwarts.school_re.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school_re.model.Student;
import ru.hogwarts.school_re.service.StudentService;

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

    @GetMapping("/count-all")
    public ResponseEntity getCountAllStudents() {
        return ResponseEntity.ok("" + studentService.getCountAllStudents());
    }

    @GetMapping("/avg-age")
    public ResponseEntity getAverageOfStudentsAge() {
        return ResponseEntity.ok(studentService.getAverageOfStudentsAge());
    }

    @GetMapping("/age={age}")
    public ResponseEntity filterStudentsByAge(@PathVariable int age) {
        if (studentService.filterStudentsByAge(age).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(studentService.filterStudentsByAge(age));
    }

    @GetMapping("/last-students")
    public ResponseEntity getLast5StudentsSorted() {
        return ResponseEntity.ok(studentService.getLast5StudentsSorted());
    }

    @PutMapping
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

    @GetMapping
    public ResponseEntity filterStudentBetweenAge(@RequestParam(required = true) int min,
                                                  @RequestParam(required = true) int max) {
        return ResponseEntity.ok(studentService.findStudentsByAgeBetween(min, max));
    }

    @GetMapping("/getNamesWithChar")
    public ResponseEntity getNamesWithChar(String filterChar) {
        return ResponseEntity.ok(studentService.getNamesWithChar(filterChar));
    }

    @GetMapping("/averageAge")
    public ResponseEntity getAverageStudentsAge() {
        return ResponseEntity.ok(studentService.getAverageStudentsAge());
    }

    @GetMapping("/threadTest1")
    public void threadTest1() {
        studentService.threadTest1();
    }

    @GetMapping("/threadTest2")
    public void threadTest2() {
        studentService.threadTest2();
    }
}
