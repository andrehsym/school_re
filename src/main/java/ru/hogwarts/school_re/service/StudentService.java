package ru.hogwarts.school_re.service;


import ru.hogwarts.school_re.model.Student;

import java.util.Collection;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudent(long id);

    Collection<Student> getStudentCollection();

    Student editStudent(Student student);

    void removeStudent(long id);

    Collection<Student> filterStudentsByAge(int age);
}
