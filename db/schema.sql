CREATE TABLE job.candidate
(
    id bigint NOT NULL,
    name text COLLATE pg_catalog."default",
    experience text COLLATE pg_catalog."default",
    salary text COLLATE pg_catalog."default",
    CONSTRAINT candidate_pkey PRIMARY KEY (id)
);