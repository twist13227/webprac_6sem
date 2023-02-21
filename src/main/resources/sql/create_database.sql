DROP SCHEMA IF EXISTS schedule CASCADE;
CREATE SCHEMA schedule;

CREATE TABLE schedule.groups (
    group_id SERIAL PRIMARY KEY,
    faculty_name text NOT NULL,
    course integer CHECK(course >= 1 AND course <= 6),
    group_number text NOT NULL
);

CREATE TABLE schedule.students (
    student_id SERIAL PRIMARY KEY,
    surname text NOT NULL,
    name text NOT NULL,
    patronymic text,
    group_id integer CONSTRAINT student_group REFERENCES schedule.groups ON DELETE CASCADE
);

CREATE TABLE schedule.subjects (
    subject_id SERIAL PRIMARY KEY,
    subject_name text NOT NULL,
    faculty_name text
);

CREATE TABLE schedule.examiners (
    examiner_id SERIAL PRIMARY KEY,
    surname text NOT NULL,
    name text NOT NULL,
    patronymic text
);

CREATE TABLE schedule.timedate (
    timedate_id SERIAL PRIMARY KEY,
    building_name text NOT NULL,
    audience_num text NOT NULL,
    "date" timestamp NOT NULL
);

CREATE TABLE schedule.consultation (
    consultation_id SERIAL PRIMARY KEY,
    timedate_id integer CONSTRAINT cons_timedate REFERENCES schedule.timedate
);

CREATE TABLE schedule.exams (
    exam_id SERIAL PRIMARY KEY,
    subject_id integer CONSTRAINT exam_subject REFERENCES schedule.subjects ON DELETE CASCADE,
    timedate_id integer CONSTRAINT exam_timedate REFERENCES schedule.timedate ON UPDATE CASCADE,
    consultation_id integer DEFAULT NULL CONSTRAINT exam_consultation REFERENCES schedule.consultation ON DELETE SET NULL
);

CREATE TABLE schedule.groups_exams (
    exam_id integer CONSTRAINT gre_exam REFERENCES schedule.exams ON DELETE CASCADE,
    group_id integer CONSTRAINT gre_group REFERENCES schedule.groups ON DELETE CASCADE,
    UNIQUE (exam_id, group_id)
);

CREATE TABLE schedule.exams_examiners (
    exam_id integer CONSTRAINT ee_exam REFERENCES schedule.exams ON DELETE CASCADE,
    examiner_id integer CONSTRAINT ee_examiner REFERENCES schedule.examiners ON DELETE CASCADE,
    UNIQUE (exam_id, examiner_id)
);

CREATE TABLE schedule.examiners_subjects (
    subject_id integer CONSTRAINT es_subject REFERENCES schedule.subjects ON DELETE CASCADE,
    examiner_id integer CONSTRAINT es_examiner REFERENCES schedule.examiners ON DELETE CASCADE,
    UNIQUE (subject_id, examiner_id)
);
