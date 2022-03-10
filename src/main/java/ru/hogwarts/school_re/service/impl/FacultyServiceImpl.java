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

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Faculty> getFacultyCollection() { //get all students
        return facultyRepository.findAll();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void removeFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> filterFacultiesByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }

    @Override
    public Faculty findFacultyByName(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }
}
