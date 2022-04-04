package ru.hogwarts.school_re.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school_re.model.Student;
import ru.hogwarts.school_re.repository.StudentRepository;
import ru.hogwarts.school_re.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

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

    @Override
    public List<String> getNamesWithChar(String filterChar) {
        return studentRepository.findAll()
                .parallelStream()
                .map(Student::getName)
                .sorted()
                .map(i -> i.substring(0,1).toUpperCase()+i.substring(1))
                .filter(i -> i.substring(0,1).equalsIgnoreCase(filterChar))
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageStudentsAge() {
        return studentRepository.findAll()
                .parallelStream()
                .mapToInt(Student::getAge)
                .average()
                .getAsDouble();
    }

    @Override
    public void getStudentsThreads() {
        printStudent(6l);
        printStudent(7l);
        printStudent(5l);
        printStudent(9l);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is created");
            printStudent(10l);
            printStudent(11l);
            printStudent(8l);
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is created");
            printStudent(118l);
            printStudent(119l);
            printStudent(120l);
        }).start();
    }

    @Override
    public void getStudentsSyncThreads() {
        printStudentSync(6l);
        printStudentSync(7l);
        printStudentSync(5l);
        printStudentSync(9l);

        new Thread(() -> {
            System.out.println("Synchronized " + Thread.currentThread().getName() + " is created");
            printStudentSync(10l);
            printStudentSync(11l);
            printStudentSync(8l);
        }).start();

        new Thread(() -> {
            System.out.println("Synchronized " + Thread.currentThread().getName() + " is created");
            printStudentSync(118l);
            printStudentSync(119l);
            printStudentSync(120l);
        }).start();
    }

    public void printStudent(Long id) {
        System.out.println(studentRepository.getById(id));
    }

    public synchronized void printStudentSync(Long id) {
        System.out.println(studentRepository.getById(id));
    }



}



