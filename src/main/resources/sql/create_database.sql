CREATE DATABASE prac;

DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE TABLE public.residence (
    residence_id SERIAL PRIMARY KEY,
    country text NOT NULL,
	town text NOT NULL,
	address text NOT NULL,
	description text
);

CREATE TABLE public.person (
    person_id SERIAL PRIMARY KEY,
    surname text NOT NULL,
    name text NOT NULL,
    patronymic text,
	gender text NOT NULL,
	birth_date text NOT NULL,
	death_date text,
	characteristics text
);

CREATE TABLE public.person_residence (
    node_id SERIAL PRIMARY KEY,
	person_id integer REFERENCES public.person ON DELETE CASCADE,
	residence_id integer REFERENCES public.residence ON DELETE CASCADE
);

CREATE TABLE public.relation (
    relation_id SERIAL PRIMARY KEY,
    first_person_id integer REFERENCES public.person ON DELETE CASCADE,
	second_person_id integer REFERENCES public.person ON DELETE CASCADE,
	relation_type integer NOT NULL,
	information text
);

