CREATE TABLE IF NOT EXISTS job.candidate
(
    id           bigint NOT NULL,
    name         text COLLATE pg_catalog."default",
    experience   text COLLATE pg_catalog."default",
    salary       text COLLATE pg_catalog."default",
    vacancydb_id bigint,
    CONSTRAINT candidate_pkey PRIMARY KEY (id),
    CONSTRAINT "vacancyDb_fkey" FOREIGN KEY (vacancydb_id)
        REFERENCES job.vacancydb (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS job.vacancydb
(
    id   bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT vacancydb_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS job.vacancy
(
    id   bigint NOT NULL,
    name text COLLATE pg_catalog."default",
    CONSTRAINT vacancy_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS job.vacancydb_vacancy
(
    vacancydb_id bigint NOT NULL,
    vacancies_id bigint NOT NULL,
    CONSTRAINT uk_lmff39ay1uoinurhv3l3pde2a UNIQUE (vacancies_id),
    CONSTRAINT uk_t42f7xvkdkm9a8fko58glq5dw UNIQUE (vacancies_id),
    CONSTRAINT fk7fqhp467msid6q5p1ysckd014 FOREIGN KEY (vacancies_id)
        REFERENCES job.vacancy (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkfxhbu519utl0byjw2y0pmbnrs FOREIGN KEY (vacancydb_id)
        REFERENCES job.vacancydb (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkn2mgt1ytwxvq833pyhswc67x0 FOREIGN KEY (vacancydb_id)
        REFERENCES job.vacancydb (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);