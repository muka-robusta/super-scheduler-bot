CREATE SEQUENCE IF NOT EXISTS events_seq;
CREATE TABLE IF NOT EXISTS events (
    id bigint PRIMARY KEY DEFAULT nextval('events_seq'),
    name varchar(255),
    description text,
    time TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS subject_seq;
CREATE TABLE IF NOT EXISTS subjects (
    id bigint PRIMARY KEY DEFAULT nextval('subject_seq'),
    name varchar(255),
    description text,
    tutor_name varchar(255)
);

CREATE SEQUENCE IF NOT EXISTS schedule_event_seq;
CREATE TABLE IF NOT EXISTS schedule_events (
    id bigint PRIMARY KEY DEFAULT nextval('schedule_event_seq'),
    subject_id bigint,
    order_value integer,
    day_of_week varchar(63),
    event_time time,
    CONSTRAINT subject_id_fk
        FOREIGN KEY (subject_id)
        REFERENCES subjects(id)
)