package ru.hogwarts.school_re.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school_re.model.Student;
import ru.hogwarts.school_re.repository.StudentRepository;
import ru.hogwarts.school_re.service.StudentService;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override //POST
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override //GET
    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override //GET
    public Collection<Student> getStudentCollection() { //get all students
        return studentRepository.findAll();
    }

    @Override //PUT
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override //DELETE
    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override //GET
    public Collection<Student> filterStudentsByAge(int age) {
        return studentRepository.findByAge(age);
    }

    @Override //GET
    public Collection<Student> findStudentsByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }
}
