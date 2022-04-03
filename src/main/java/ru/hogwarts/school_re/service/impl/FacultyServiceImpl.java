package ru.hogwarts.school_re.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school_re.model.Faculty;
import ru.hogwarts.school_re.repository.FacultyRepository;
import ru.hogwarts.school_re.service.FacultyService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

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
        logger.info("Was invoked method to find the faculty by id {}", id);
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
        logger.info("Was invoked method to remove faculty with id {}", id);
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> filterFacultiesByColor(String color) {
        logger.info("Was invoked method for filter faculties by color {}", color);
        return facultyRepository.findByColorIgnoreCase(color);
    }

    @Override
    public Faculty findFacultyByName(String name) {
        logger.info("Was invoked method to find the faculty by name {}", name);
        return facultyRepository.findByNameIgnoreCase(name);
    }

    @Override
    public String longestFacultyName() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .get();
    }

    @Override
    public Integer mathTask() {
        logger.info("Was invoked mathTask method");
        long m = System.currentTimeMillis();
        int sum = IntStream
                .iterate(1, a -> a +1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b );
        long s = System.currentTimeMillis() - m;
        logger.info("Time {}", s);
        return sum;

//        logger.info("Was invoked mathTask method");
//        long m = System.currentTimeMillis();
//        List<Integer> streamList = Stream.iterate(1, a->a+1).limit(1_000_000).collect(Collectors.toList());
//        int sum = streamList.parallelStream().mapToInt(e->e).sum();
//        long s = System.currentTimeMillis() - m;
//        logger.info("Time {}", s);
//        return sum;
    }
}
