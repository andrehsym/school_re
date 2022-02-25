package ru.hogwarts.school_re.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school_re.model.Faculty;
import ru.hogwarts.school_re.repository.FacultyRepository;
import ru.hogwarts.school_re.service.FacultyService;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override //POST
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override //GET
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override //GET
    public Collection<Faculty> getFacultyCollection() { //get all students
        return facultyRepository.findAll();
    }

    @Override //PUT
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override //DELETE
    public void removeFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override //GET
    public Collection<Faculty> filterFacultiesByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    @Override //GET
    public Faculty findFacultyByName(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }
}
