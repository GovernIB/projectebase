
--
-- TOC entry 150 (class 1259 OID 69544)
-- Name: ${prefix}_fitxer; Type: TABLE; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

CREATE TABLE ${prefix}_fitxer (
    fitxerid bigint DEFAULT nextval('${prefix}_${artifactId}_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(45) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE ${artifactId}.${prefix}_fitxer OWNER TO ${artifactId};

--
-- TOC entry 169 (class 1259 OID 92635)
-- Name: ${prefix}_idioma; Type: TABLE; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

CREATE TABLE ${prefix}_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE ${artifactId}.${prefix}_idioma OWNER TO ${artifactId};

--
-- TOC entry 184 (class 1259 OID 210385)
-- Name: ${prefix}_traduccio; Type: TABLE; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

CREATE TABLE ${prefix}_traduccio (
    traduccioid bigint DEFAULT nextval('${prefix}_${artifactId}_seq'::regclass) NOT NULL
);


ALTER TABLE ${artifactId}.${prefix}_traduccio OWNER TO ${artifactId};

--
-- TOC entry 183 (class 1259 OID 210326)
-- Name: ${prefix}_traducciomap; Type: TABLE; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

CREATE TABLE ${prefix}_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE ${artifactId}.${prefix}_traducciomap OWNER TO ${artifactId};

--
-- TOC entry 1836 (class 2606 OID 70326)
-- Name: ${prefix}_fitxer_pk; Type: CONSTRAINT; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

ALTER TABLE ONLY ${prefix}_fitxer
    ADD CONSTRAINT ${prefix}_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1839 (class 2606 OID 96099)
-- Name: ${prefix}_idioma_pk; Type: CONSTRAINT; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

ALTER TABLE ONLY ${prefix}_idioma
    ADD CONSTRAINT ${prefix}_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1846 (class 2606 OID 210396)
-- Name: ${prefix}_traduccio_pk; Type: CONSTRAINT; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

ALTER TABLE ONLY ${prefix}_traduccio
    ADD CONSTRAINT ${prefix}_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1842 (class 2606 OID 210501)
-- Name: ${prefix}_traducciomap_pk; Type: CONSTRAINT; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

ALTER TABLE ONLY ${prefix}_traducciomap
    ADD CONSTRAINT ${prefix}_traducciomap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1837 (class 1259 OID 202159)
-- Name: ${prefix}_fitxer_pk_i; Type: INDEX; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

CREATE INDEX ${prefix}_fitxer_pk_i ON ${prefix}_fitxer USING btree (fitxerid);


--
-- TOC entry 1840 (class 1259 OID 202163)
-- Name: ${prefix}_idioma_pk_i; Type: INDEX; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

CREATE INDEX ${prefix}_idioma_pk_i ON ${prefix}_idioma USING btree (idiomaid);


--
-- TOC entry 1847 (class 1259 OID 210461)
-- Name: ${prefix}_traduccio_pk_i; Type: INDEX; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

CREATE INDEX ${prefix}_traduccio_pk_i ON ${prefix}_traduccio USING btree (traduccioid);


--
-- TOC entry 1843 (class 1259 OID 210529)
-- Name: ${prefix}_traducmap_idiomaid_pk_i; Type: INDEX; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

CREATE INDEX ${prefix}_traducmap_idiomaid_pk_i ON ${prefix}_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1844 (class 1259 OID 210528)
-- Name: ${prefix}_traducmap_tradmapid_pk_i; Type: INDEX; Schema: ${artifactId}; Owner: ${artifactId}; Tablespace: 
--

CREATE INDEX ${prefix}_traducmap_tradmapid_pk_i ON ${prefix}_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1848 (class 2606 OID 210469)
-- Name: ${prefix}_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: ${artifactId}; Owner: ${artifactId}
--

ALTER TABLE ONLY ${prefix}_traducciomap
    ADD CONSTRAINT ${prefix}_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES ${prefix}_traduccio(traduccioid);

