package ru.hogwarts.school_re.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school_re.model.Student;
import ru.hogwarts.school_re.repository.StudentRepository;
import ru.hogwarts.school_re.service.StudentService;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id) {
        logger.info("Was invoked method to find the student");
        return studentRepository.findById(id).get();
    }

    @Override
    public Collection<Student> getStudentCollection() {
        logger.info("Was invoked method for collection of students");
        return studentRepository.findAll();
    }

    @Override
    public Integer getCountAllStudents() {
        logger.info("Was invoked method to count students");
        return studentRepository.getCountAllStudents();
    }

    @Override
    public Integer getAverageOfStudentsAge() {
        logger.info("Was invoked method to get the average age of the students");
        return studentRepository.getAverageOfStudentsAge();
    }


    @Override
    public Collection<Student> getLast5StudentsSorted() {
        logger.info("Was invoked method to get last 5 sorted students");
        return studentRepository.getLast5StudentsSorted();
    }


    @Override
    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(Long id) {
        logger.info("Was invoked method for remove student");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> filterStudentsByAge(int age) {
        logger.info("Was invoked method for filter students by age");
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> findStudentsByAgeBetween(int min, int max) {
        logger.info("Was invoked method to find students in range of age");
        return studentRepository.findByAgeBetween(min, max);
    }
}
