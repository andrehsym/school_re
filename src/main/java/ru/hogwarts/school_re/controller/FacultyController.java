package ru.hogwarts.school_re.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school_re.model.Faculty;
import ru.hogwarts.school_re.service.FacultyService;

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

    @GetMapping("/all")
    public ResponseEntity getFacultyCollection(@RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.getFacultyCollection());
    }

    @GetMapping("{color}")
    public ResponseEntity filterFacultiesByColor(@PathVariable String color) {
        if (facultyService.filterFacultiesByColor(color).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facultyService.filterFacultiesByColor(color));
    }

    @PutMapping
    public ResponseEntity editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty);
        if (editFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeFaculty(@PathVariable Long id) {
        Faculty getFaculty = facultyService.findFaculty(id);
        if (getFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getFaculty(@RequestParam(required = false) Long id,
                                     @RequestParam(required = false) String name) {
        if (name!= null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultyByName(name));
        }
        return ResponseEntity.ok(facultyService.findFaculty(id));
    }

}
