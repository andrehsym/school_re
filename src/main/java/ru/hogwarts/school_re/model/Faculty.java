package ru.hogwarts.school_re.model;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Set;

@Entity
public class Faculty {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private Set<Student> students;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && name.equals(faculty.name) && color.equals(faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
