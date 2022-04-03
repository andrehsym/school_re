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
    public void threadTest1() {
        List<String> names = studentRepository.getStudentsNames();

        Thread thread1 = new Thread(() -> {
            System.out.println(names.get(3));
            System.out.println(names.get(4));
            System.out.println(names.get(5));
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            System.out.println(names.get(6));
            System.out.println(names.get(7));
            System.out.println(names.get(8));
            System.out.println(names.get(9));
        });
        thread2.start();
        System.out.println(names.get(0));
        System.out.println(names.get(1));
        System.out.println(names.get(2));

            //вариант 2
//        stNames(6L);
//        stNames(7L);
//        stNames(5L);
//        Thread thread1 = new Thread(() -> {
//            stNames(9L);
//            stNames(10L);
//            stNames(11L);
//        });
//        Thread thread2 = new Thread(() -> {
//            stNames(8L);
//            stNames(118L);
//            stNames(119L);
//            stNames(120L);
//        });
//        thread1.start();
//        thread2.start();
//        System.out.println(names.get(0));
//        System.out.println(names.get(1));
//        System.out.println(names.get(2));

        //вариант 3
//        Thread thread1 = new Thread(() -> {
//            System.out.println(studentRepository.getById(9L).getName());
//            System.out.println(studentRepository.getById(10L).getName());
//            System.out.println(studentRepository.getById(11L).getName());
//        });
//        Thread thread2 = new Thread(() -> {
//            System.out.println(studentRepository.getById(8L).getName());
//            System.out.println(studentRepository.getById(118L).getName());
//            System.out.println(studentRepository.getById(119L).getName());
//            System.out.println(studentRepository.getById(120L).getName());
//        });
//        thread1.start();
//        thread2.start();
//        System.out.println(studentRepository.getById(6L).getName());
//        System.out.println(studentRepository.getById(7L).getName());
//        System.out.println(studentRepository.getById(5L).getName());

    }

    @Override
    public void threadTest2() {
        Thread thread1 = new Thread(() -> {
            synchNames(9L);
            synchNames(10L);
            synchNames(11L);
        });
        Thread thread2 = new Thread(() -> {
            synchNames(8L);
            synchNames(118L);
            synchNames(119L);
            synchNames(120L);
        });
        thread1.start();
        thread2.start();
        synchNames(6L);
        synchNames(7L);
        synchNames(5L);

//        Thread thread1 = new Thread(() -> {
//            synchNames(3);
//            synchNames(4);
//            synchNames(5);
//        });
//        Thread thread2 = new Thread(() -> {
//            synchNames(6);
//            synchNames(7);
//            synchNames(8);
//            synchNames(9);
//        });
//        thread1.start();
//        thread2.start();
//        synchNames(0);
//        synchNames(1);
//        synchNames(2);
    }

    private synchronized void synchNames(Long /*Integer*/ id) {
//        String name = studentRepository.getStudentsNames().get(id);
        String name = studentRepository.getById(id).getName();
        System.out.print(name);
    }

}



