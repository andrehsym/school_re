package ru.hogwarts.school_re.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school_re.model.Faculty;
import ru.hogwarts.school_re.repository.FacultyRepository;
import ru.hogwarts.school_re.service.FacultyService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
//    Map<Long, Faculty> faculties = new HashMap<>();
    long countFacultyId = 0;

    private Faculty createFaculty(String name, String color) {
        Faculty newFaculty = new Faculty(countFacultyId, name, color);
        faculties.put(countFacultyId, newFaculty);
        ++countFacultyId;
        return newFaculty;
    }

    @Override //POST
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++countFacultyId);
        faculties.put(countFacultyId, faculty);
        return faculty;
    }

    @Override //GET
    public Faculty findFaculty(long id) { //get one student
        return faculties.get(id);
    }

    @Override //GET
    public Map<Long, Faculty> getFacultyCollection() { //get all students
        return faculties;
    }

    @Override //PUT
    public Faculty editFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override //DELETE
    public Faculty removeFaculty(long id) { //remove
        return faculties.remove(id);
    }

    @Override //GET
    public Set<Faculty> filterFacultiesByColor(String color) {
//        Set<Faculty> filterFacultiesByColor = new HashSet<>();
//        for (Faculty faculty: faculties.values()) {
//            if (faculty.getColor().equals(color)) {
//                filterFacultiesByColor.add(faculty);
//            }
//        }
//        return filterFacultiesByColor;
        return faculties.values()
                .stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toSet());
    }

    @PostConstruct
    public void init() {
        createFaculty("Gryffindor", "red");
        createFaculty("Hufflepuff", "grey");
        createFaculty("Ravenclaw", "blue");
        createFaculty("Slytherin", "green");
    }
}
