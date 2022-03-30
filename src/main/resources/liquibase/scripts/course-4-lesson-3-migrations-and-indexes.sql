-- liquibase formatted sql

-- changeSet andrehsym:1

create index student_name_search on student (name);

-- changeSet andrehsym:2

create index faculty_name_and_color_search on faculty (name, color);


