

CREATE SEQUENCE pbs_projectebasearchetype_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;

CREATE TABLE pbs_fitxer (
    fitxerid bigint DEFAULT nextval('pbs_projectebasearchetype_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);

CREATE TABLE pbs_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);

CREATE TABLE pbs_traduccio (
    traduccioid bigint DEFAULT nextval('pbs_projectebasearchetype_seq'::regclass) NOT NULL
);

CREATE TABLE pbs_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);

ALTER TABLE ONLY pbs_fitxer
    ADD CONSTRAINT pbs_fitxer_pk PRIMARY KEY (fitxerid);

ALTER TABLE ONLY pbs_idioma
    ADD CONSTRAINT pbs_idioma_pk PRIMARY KEY (idiomaid);

ALTER TABLE ONLY pbs_traduccio
    ADD CONSTRAINT pbs_traduccio_pk PRIMARY KEY (traduccioid);

ALTER TABLE ONLY pbs_traducciomap
    ADD CONSTRAINT pbs_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);

CREATE INDEX pbs_fitxer_pk_i ON pbs_fitxer USING btree (fitxerid);

CREATE INDEX pbs_idioma_pk_i ON pbs_idioma USING btree (idiomaid);

CREATE INDEX pbs_traduccio_pk_i ON pbs_traduccio USING btree (traduccioid);

CREATE INDEX pbs_traducciomap_idiomaid_fk_i ON pbs_traducciomap USING btree (idiomaid);

CREATE INDEX pbs_traducciomap_pk_i ON pbs_traducciomap USING btree (traducciomapid);

ALTER TABLE ONLY pbs_traducciomap
    ADD CONSTRAINT pbs_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES pbs_traduccio(traduccioid);

INSERT INTO pbs_idioma(idiomaid, nom, ordre) VALUES ('ca', 'Català', 0);
INSERT INTO pbs_idioma(idiomaid, nom, ordre) VALUES ('es', 'Castellano', 1);
    
    
