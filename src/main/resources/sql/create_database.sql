DROP SCHEMA IF EXISTS relationship CASCADE;
CREATE SCHEMA relationship;

CREATE TABLE relationship.residence (
    residence_id SERIAL PRIMARY KEY,
    country text NOT NULL,
	town text NOT NULL,
	address text NOT NULL,
	description text
);

CREATE TABLE relationship.person (
    person_id SERIAL PRIMARY KEY,
    surname text NOT NULL,
    name text NOT NULL,
    patronymic text,
	gender text NOT NULL,
	birth_date timestamp NOT NULL,
	death_date timestamp,
	characteristics text
);

CREATE TABLE relationship.person_residence (
	person_id integer REFERENCES relationship.person ON DELETE CASCADE,
	residence_id integer REFERENCES relationship.residence ON DELETE CASCADE
);

CREATE TABLE relationship.relation (
    relation_id SERIAL PRIMARY KEY,
    first_person_id integer REFERENCES relationship.person ON DELETE CASCADE,
	second_person_id integer REFERENCES relationship.person ON DELETE CASCADE,
	relation_type text NOT NULL,
	information text
);

