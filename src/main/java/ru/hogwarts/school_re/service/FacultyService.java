package ru.hogwarts.school_re.service;

import ru.hogwarts.school_re.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Collection<Faculty> getFacultyCollection();

    Faculty editFaculty(Faculty faculty);

    void removeFaculty(long id);

    Collection<Faculty> filterFacultiesByColor(String color);
}
