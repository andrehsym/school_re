package ru.hogwarts.school_re.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school_re.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value= "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getCountAllStudents();

    @Query(value= "SELECT AVG(age) FROM student", nativeQuery = true)
    Integer getAverageOfStudentsAge();

    @Query(value= "SELECT * FROM student ORDER BY id OFFSET 2", nativeQuery = true)
    Collection<Student> getLast5StudentsSorted();

    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);
}
