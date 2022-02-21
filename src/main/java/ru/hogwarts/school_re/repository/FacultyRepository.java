package ru.hogwarts.school_re.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school_re.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByColor(String color);
}
