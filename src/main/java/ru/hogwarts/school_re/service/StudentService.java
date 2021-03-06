package ru.hogwarts.school_re.service;


import ru.hogwarts.school_re.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudent(Long id);

    Collection<Student> getStudentCollection();

    Integer getCountAllStudents();

    Integer getAverageOfStudentsAge();

    Collection<Student> getLast5StudentsSorted();

    Student editStudent(Student student);

    void removeStudent(Long id);

    Collection<Student> filterStudentsByAge(int age);

    Collection<Student> findStudentsByAgeBetween(int min, int max);

    List<String> getNamesWithChar(String filterChar);

    Double getAverageStudentsAge();

//    void threadTest1();

//    void threadTest2();

    void getStudentsThreads();

    void getStudentsSyncThreads();
}
