
    create sequence PBS_PROCEDIMENT_SEQ start with 1 increment by  1;
    create sequence PBS_UNITATORGANICA_SEQ start with 1 increment by  1;

    create table PBS_PROCEDIMENT (
        ID number(19,0) not null,
        CODISIA varchar2(8 char) not null,
        NOM varchar2(50 char) not null,
        UNITATORGANICAID number(19,0) not null
    );

    create table PBS_UNITATORGANICA (
        ID number(19,0) not null,
        CODIDIR3 varchar2(9 char) not null,
        DATACREACIO date not null,
        ESTAT number(10,0) not null,
        NOM varchar2(50 char) not null
    );

    create index PBS_PROCEDIMENT_PK_I on PBS_PROCEDIMENT (ID);
    create index PBS_PROCEDIMENT_CODISIA_UK_I on PBS_PROCEDIMENT (CODISIA);
    create index PBS_PROCEDIMENT_UNITAT_FK_I on PBS_PROCEDIMENT (UNITATORGANICAID);

    alter table PBS_PROCEDIMENT
        add constraint PBS_PROCEDIMENT_PK primary key (ID);

    alter table PBS_PROCEDIMENT
        add constraint PBS_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    create index PBS_UNITAT_PK_I on PBS_UNITATORGANICA (ID);
    create index PBS_UNITAT_CODIDIR3_UK_I on PBS_UNITATORGANICA (CODIDIR3);

    alter table PBS_UNITATORGANICA
        add constraint PBS_UNITAT_PK primary key (ID);

    alter table PBS_UNITATORGANICA
        add constraint PBS_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table PBS_PROCEDIMENT
        add constraint PBS_PROCEDIMENT_UNITAT_FK
        foreign key (UNITATORGANICAID)
        references PBS_UNITATORGANICA;

    -- Grants per l'usuari www_projectebase
    -- seqüències
    GRANT SELECT, ALTER ON PBS_PROCEDIMENT_SEQ TO WWW_PROJECTEBASE;
    GRANT SELECT, ALTER ON PBS_UNITATORGANICA_SEQ TO WWW_PROJECTEBASE;
    -- taules
    GRANT SELECT, INSERT, UPDATE, DELETE ON PBS_PROCEDIMENT TO WWW_PROJECTEBASE;
    GRANT SELECT, INSERT, UPDATE, DELETE ON PBS_UNITATORGANICA TO WWW_PROJECTEBASE;


