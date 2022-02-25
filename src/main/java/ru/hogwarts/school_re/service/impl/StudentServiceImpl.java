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

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Collection<Student> getStudentCollection() { //get all students
        return studentRepository.findAll();
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> filterStudentsByAge(int age) {
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> findStudentsByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }
}
