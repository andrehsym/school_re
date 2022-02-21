package ru.hogwarts.school_re.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school_re.model.Faculty;
import ru.hogwarts.school_re.service.FacultyService;

import java.util.Set;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity getFaculty(@PathVariable long id) {
        Faculty getFaculty = facultyService.findFaculty(id);
        if (getFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getFaculty);
    }

    @GetMapping("/all")
    public ResponseEntity getAllFacultyCollection() {
        return ResponseEntity.ok(facultyService.getFacultyCollection());
    }

    @GetMapping("/color={color}")
    public ResponseEntity filterFacultiesByColor(@PathVariable String color) {
        Set<Faculty> filterFacultiesByColor = Set.copyOf(facultyService.filterFacultiesByColor(color));
        if (filterFacultiesByColor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filterFacultiesByColor);
    }

    @PutMapping()
    public ResponseEntity editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty);
        if (editFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeFaculty(@PathVariable long id) {
        Faculty getFaculty = facultyService.findFaculty(id);
        if (getFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }
}
