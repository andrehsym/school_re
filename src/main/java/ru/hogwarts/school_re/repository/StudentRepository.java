package ru.hogwarts.school_re.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school_re.model.Student;

import java.util.Collection;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);
}
