package ru.hogwarts.school_re.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school_re.model.Student;
import ru.hogwarts.school_re.service.StudentService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    Map<Long, Student> students = new HashMap<>();
    long countStudentId = 0;

    private Student createStudent(String name, int age) {
        Student newStudent = new Student(countStudentId, name, age);
        students.put(countStudentId, newStudent);
        ++countStudentId;
        return newStudent;
    }

    @Override //POST
    public Student createStudent(Student student) {
        student.setId(++countStudentId);
        students.put(countStudentId, student);
        return student;
    }

    @Override //GET
    public Student findStudent(long id) { //get one student
        return students.get(id);
    }

    @Override //GET
    public Map<Long, Student> getStudentCollection() { //get all students
        return students;
    }

    @Override //PUT
    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override //DELETE
    public Student removeStudent(long id) { //remove
        return students.remove(id);
    }

    @Override //GET
    public Set<Student> filterStudentsByAge(int age) {
        if (age < 0) {
            throw new RuntimeException();
        }
        Set<Student> filterStudentsByAge = new HashSet<>();
        for (Student student: students.values()) {
            if (student.getAge() == age) {
                filterStudentsByAge.add(student);
            }
        }
        return filterStudentsByAge;
    }

    @PostConstruct
    public void init() {
        createStudent("Sasha", 23);
        createStudent("Masha", 23);
        createStudent("Dima", 20);
        createStudent("Andre", 21);
    }
}
