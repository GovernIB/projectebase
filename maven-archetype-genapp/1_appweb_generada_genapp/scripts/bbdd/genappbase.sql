--
-- PostgreSQL database dump
--

-- Dumped from database version 8.4.14
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-08-25 12:39:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = projectebasearchetype, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 150 (class 1259 OID 69544)
-- Name: pbs_fitxer; Type: TABLE; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

CREATE TABLE pbs_fitxer (
    fitxerid bigint DEFAULT nextval('pbs_projectebasearchetype_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(45) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE projectebasearchetype.pbs_fitxer OWNER TO projectebasearchetype;

--
-- TOC entry 169 (class 1259 OID 92635)
-- Name: pbs_idioma; Type: TABLE; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

CREATE TABLE pbs_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE projectebasearchetype.pbs_idioma OWNER TO projectebasearchetype;

--
-- TOC entry 184 (class 1259 OID 210385)
-- Name: pbs_traduccio; Type: TABLE; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

CREATE TABLE pbs_traduccio (
    traduccioid bigint DEFAULT nextval('pbs_projectebasearchetype_seq'::regclass) NOT NULL
);


ALTER TABLE projectebasearchetype.pbs_traduccio OWNER TO projectebasearchetype;

--
-- TOC entry 183 (class 1259 OID 210326)
-- Name: pbs_traducciomap; Type: TABLE; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

CREATE TABLE pbs_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE projectebasearchetype.pbs_traducciomap OWNER TO projectebasearchetype;

--
-- TOC entry 1836 (class 2606 OID 70326)
-- Name: pbs_fitxer_pk; Type: CONSTRAINT; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

ALTER TABLE ONLY pbs_fitxer
    ADD CONSTRAINT pbs_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1839 (class 2606 OID 96099)
-- Name: pbs_idioma_pk; Type: CONSTRAINT; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

ALTER TABLE ONLY pbs_idioma
    ADD CONSTRAINT pbs_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1846 (class 2606 OID 210396)
-- Name: pbs_traduccio_pk; Type: CONSTRAINT; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

ALTER TABLE ONLY pbs_traduccio
    ADD CONSTRAINT pbs_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1842 (class 2606 OID 210501)
-- Name: pbs_traducciomap_pk; Type: CONSTRAINT; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

ALTER TABLE ONLY pbs_traducciomap
    ADD CONSTRAINT pbs_traducciomap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1837 (class 1259 OID 202159)
-- Name: pbs_fitxer_pk_i; Type: INDEX; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

CREATE INDEX pbs_fitxer_pk_i ON pbs_fitxer USING btree (fitxerid);


--
-- TOC entry 1840 (class 1259 OID 202163)
-- Name: pbs_idioma_pk_i; Type: INDEX; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

CREATE INDEX pbs_idioma_pk_i ON pbs_idioma USING btree (idiomaid);


--
-- TOC entry 1847 (class 1259 OID 210461)
-- Name: pbs_traduccio_pk_i; Type: INDEX; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

CREATE INDEX pbs_traduccio_pk_i ON pbs_traduccio USING btree (traduccioid);


--
-- TOC entry 1843 (class 1259 OID 210529)
-- Name: pbs_traducmap_idiomaid_pk_i; Type: INDEX; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

CREATE INDEX pbs_traducmap_idiomaid_pk_i ON pbs_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1844 (class 1259 OID 210528)
-- Name: pbs_traducmap_tradmapid_pk_i; Type: INDEX; Schema: projectebasearchetype; Owner: projectebasearchetype; Tablespace: 
--

CREATE INDEX pbs_traducmap_tradmapid_pk_i ON pbs_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1848 (class 2606 OID 210469)
-- Name: pbs_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: projectebasearchetype; Owner: projectebasearchetype
--

ALTER TABLE ONLY pbs_traducciomap
    ADD CONSTRAINT pbs_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES pbs_traduccio(traduccioid);


-- Completed on 2014-08-25 12:39:21

--
-- PostgreSQL database dump complete
--

