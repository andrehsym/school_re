package ru.hogwarts.school_re.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school_re.model.Faculty;
import ru.hogwarts.school_re.service.FacultyService;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity createFaculty(@RequestBody Faculty faculty) {
        Faculty createFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createFaculty);
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
        Map<Long, Faculty> getCollection = Map.copyOf(facultyService.getFacultyCollection());
        if (getCollection == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(getCollection);
//        return ResponseEntity.ok(studentService.getStudentCollection());
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
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeFaculty(@PathVariable long id) {
        Faculty getFaculty = facultyService.findFaculty(id);
        if (getFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.removeFaculty(id));
    }
}
