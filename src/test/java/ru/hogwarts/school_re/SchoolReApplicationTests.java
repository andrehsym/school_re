package ru.hogwarts.school_re;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school_re.controller.StudentController;
import ru.hogwarts.school_re.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolReApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    private Student student1;
    private Student student2;
    private Student student3;

    private long id;
    private long id1;
    private long id2;
    private long id3;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @BeforeEach
    public void start() {
        student1 = new Student();
        student1.setAge(30);
        student1.setName("Nikolay");
        student2 = new Student();
        student2.setAge(31);
        student2.setName("Boris");
        student3 = new Student();
        student3.setAge(32);
        student3.setName("Anna");
    }

    @Test
    public void testCreateStudent() throws Exception {
        id = restTemplate.postForObject("http://localhost:" + port + "/student", student1, Student.class).getId();
        student1.setId(id);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student1, Student.class))
                .isEqualTo(student1);
        restTemplate.delete("http://localhost:" + port + "/student/" + id, student1, Student.class);
    }

    @Test
    public void testEditStudent() throws Exception {
        student1.setId(this.restTemplate.postForObject("http://localhost:" + port + "/student", student1, Student.class).getId());
        student1.setAge(999);
        restTemplate.put("http://localhost:" + port + "/student", student1);
        id = restTemplate.postForObject("http://localhost:" + port + "/student", student1, Student.class).getId();

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id, Student.class))
                .isEqualTo(student1);
        restTemplate.delete("http://localhost:" + port + "/student/" + id, student1, Student.class);
    }

    @Test
    public void testRemoveStudent() throws Exception {
        id = restTemplate.postForObject("http://localhost:" + port + "/student", student1, Student.class).getId();
        student1.setId(id);
        restTemplate.delete("http://localhost:" + port + "/student" + id, Student.class);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id, Student.class))
                .isEqualTo(student1);
        restTemplate.delete("http://localhost:" + port + "/student/" + id, student1, Student.class);
    }

    @Test
    public void testGetAllStudentCollection() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/all", Collection.class))
                .isNotNull();
    }

    @Test
    public void testGetStudent() throws Exception {
        id = restTemplate.postForObject("http://localhost:" + port + "/student", student1, Student.class).getId();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id, Student.class))
                .isNotNull();
        restTemplate.delete("http://localhost:" + port + "/student/" + id, student1, Student.class);
    }

    @Test
    public void testFilterStudentsByAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age=23", Collection.class))
                .isNotNull();
    }

    @Test
    public void filterStudentBetweenAge() throws Exception {
        id1 = restTemplate.postForObject("http://localhost:" + port + "/student", student1, Student.class).getId();
        id2 = restTemplate.postForObject("http://localhost:" + port + "/student", student2, Student.class).getId();
        id3 = restTemplate.postForObject("http://localhost:" + port + "/student", student3, Student.class).getId();
        student1.setId(id1);
        student2.setId(id2);
        student3.setId(id3);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?min=30&max=32",
                        Collection.class))
                .hasSize(3);
        restTemplate.delete("http://localhost:" + port + "/student/" + id1, Student.class);
        restTemplate.delete("http://localhost:" + port + "/student/" + id2, Student.class);
        restTemplate.delete("http://localhost:" + port + "/student/" + id3, Student.class);
    }

}
