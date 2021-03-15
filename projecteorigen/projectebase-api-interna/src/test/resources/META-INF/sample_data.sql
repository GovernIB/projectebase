
-- Crear unitats orgàniques d'exemple
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (1, 'A00000001', TO_DATE('2019-12-14', 'YYYY-MM-DD'), 0, 'Unitat 1');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (2, 'A00000002', TO_DATE('2019-12-31', 'YYYY-MM-DD'), 0, 'Unitat 2');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (3, 'A00000003', TO_DATE('2020-01-21', 'YYYY-MM-DD'), 0, 'Unitat 3');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (4, 'A00000004', TO_DATE('2019-12-26', 'YYYY-MM-DD'), 0, 'Unitat 4');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (5, 'A00000005', TO_DATE('2020-01-14', 'YYYY-MM-DD'), 0, 'Unitat 5');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (6, 'A00000006', TO_DATE('2020-01-24', 'YYYY-MM-DD'), 0, 'Unitat 6');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (7, 'A00000007', TO_DATE('2020-01-14', 'YYYY-MM-DD'), 0, 'Unitat 7');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (8, 'A00000008', TO_DATE('2019-12-13', 'YYYY-MM-DD'), 0, 'Unitat 8');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (9, 'A00000009', TO_DATE('2019-12-01', 'YYYY-MM-DD'), 0, 'Unitat 9');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (10, 'A00000010', TO_DATE('2019-12-31', 'YYYY-MM-DD'), 0, 'Unitat 10');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (11, 'A00000011', TO_DATE('2019-12-10', 'YYYY-MM-DD'), 0, 'Unitat 11');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (12, 'A00000012', TO_DATE('2020-01-15', 'YYYY-MM-DD'), 0, 'Unitat 12');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (13, 'U00000013', TO_DATE('2020-01-15', 'YYYY-MM-DD'), 0, 'Unitat 13');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (14, 'U00000014', TO_DATE('2020-01-15', 'YYYY-MM-DD'), 0, 'Unitat 14');
INSERT INTO pbs_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (15, 'U00000015', TO_DATE('2020-01-15', 'YYYY-MM-DD'), 0, 'Unitat 15');

-- Crear procediments d'exemple
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (1, '010001', 'Procediment 1', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (2, '010002', 'Procediment 2', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (3, '010003', 'Procediment 3', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (4, '010004', 'Procediment 4', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (5, '010005', 'Procediment 5', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (6, '010006', 'Procediment 6', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (7, '010007', 'Procediment 7', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (8, '010008', 'Procediment 8', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (9, '010009', 'Procediment 9', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (10, '010010', 'Procediment 10', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (11, '010011', 'Procediment 11', 1);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (12, '020001', 'Procediment 2-1', 2);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (13, '020002', 'Procediment 2-2', 2);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (14, '030001', 'Procediment 3-1', 3);
INSERT INTO pbs_procediment (id, codisia, nom, unitatorganicaid) VALUES (15, '030002', 'Procediment 3-2', 3);

-- Actualitzar seqüència de les unitats orgàniques
ALTER SEQUENCE pbs_procediment_seq INCREMENT BY 100;
SELECT pbs_procediment_seq.NEXTVAL FROM dual;
ALTER SEQUENCE pbs_procediment_seq INCREMENT BY 1;

-- Actualitzar seqüència dels procediments
ALTER SEQUENCE pbs_unitatorganica_seq INCREMENT BY 100;
SELECT pbs_unitatorganica_seq.NEXTVAL FROM dual;
ALTER SEQUENCE pbs_unitatorganica_seq INCREMENT BY 1;

