-- liquibase formatted sql

-- changeset andrehsym:1

create index student_name_search on student (name);

-- changeset andrehsym:2

create index faculty_name_&_color_search on faculty (name, color);


