package ru.hogwarts.school_re.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school_re.model.Faculty;
import ru.hogwarts.school_re.repository.FacultyRepository;
import ru.hogwarts.school_re.service.FacultyService;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        logger.info("Was invoked method to find the faculty");
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Faculty> getFacultyCollection() {
        logger.info("Was invoked method for collection of faculties");
        return facultyRepository.findAll();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void removeFaculty(Long id) {
        logger.info("Was invoked method for remove faculty");
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> filterFacultiesByColor(String color) {
        logger.info("Was invoked method for filter faculties by color");
        return facultyRepository.findByColorIgnoreCase(color);
    }

    @Override
    public Faculty findFacultyByName(String name) {
        logger.info("Was invoked method to find the faculty by name");
        return facultyRepository.findByNameIgnoreCase(name);
    }
}
