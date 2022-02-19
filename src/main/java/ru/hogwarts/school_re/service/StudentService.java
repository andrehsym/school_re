package ru.hogwarts.school_re.service;


import ru.hogwarts.school_re.model.Student;

import java.util.Map;
import java.util.Set;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudent(long id);

    Map<Long, Student> getStudentCollection();

    Student editStudent(Student student);

    Student removeStudent(long id);

    Set<Student> filterStudentsByAge(int age);
}
