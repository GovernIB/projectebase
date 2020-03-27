#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

    create sequence ${prefixuppercase}_PROCEDIMENT_SEQ start with 1 increment by  1;
    create sequence ${prefixuppercase}_UNITATORGANICA_SEQ start with 1 increment by  1;

    create table ${prefixuppercase}_PROCEDIMENT (
        ID number(19,0) not null,
        CODISIA varchar2(8 char) not null,
        NOM varchar2(50 char) not null,
        UNITATORGANICAID number(19,0) not null
    );

    create table ${prefixuppercase}_UNITATORGANICA (
        ID number(19,0) not null,
        CODIDIR3 varchar2(9 char) not null,
        DATACREACIO date not null,
        ESTAT number(10,0) not null,
        NOM varchar2(50 char) not null
    );

    create index ${prefixuppercase}_PROCEDIMENT_PK_I on ${prefixuppercase}_PROCEDIMENT (ID);
    create index ${prefixuppercase}_PROCEDIMENT_CODISIA_UK_I on ${prefixuppercase}_PROCEDIMENT (CODISIA);
    create index ${prefixuppercase}_PROCEDIMENT_UNITAT_FK_I on ${prefixuppercase}_PROCEDIMENT (UNITATORGANICAID);

    alter table ${prefixuppercase}_PROCEDIMENT
        add constraint ${prefixuppercase}_PROCEDIMENT_PK primary key (ID);

    alter table ${prefixuppercase}_PROCEDIMENT
        add constraint ${prefixuppercase}_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    create index ${prefixuppercase}_UNITAT_PK_I on ${prefixuppercase}_UNITATORGANICA (ID);
    create index ${prefixuppercase}_UNITAT_CODIDIR3_UK_I on ${prefixuppercase}_UNITATORGANICA (CODIDIR3);

    alter table ${prefixuppercase}_UNITATORGANICA
        add constraint ${prefixuppercase}_UNITAT_PK primary key (ID);

    alter table ${prefixuppercase}_UNITATORGANICA
        add constraint ${prefixuppercase}_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table ${prefixuppercase}_PROCEDIMENT
        add constraint ${prefixuppercase}_PROCEDIMENT_UNITAT_FK
        foreign key (UNITATORGANICAID)
        references ${prefixuppercase}_UNITATORGANICA;

    -- Grants per l'usuari www_${rootArtifactId}
    -- seqüències
    GRANT SELECT, ALTER ON ${prefixuppercase}_PROCEDIMENT_SEQ TO WWW_${projectnameuppercase};
    GRANT SELECT, ALTER ON ${prefixuppercase}_UNITATORGANICA_SEQ TO WWW_${projectnameuppercase};
    -- taules
    GRANT SELECT, INSERT, UPDATE, DELETE ON ${prefixuppercase}_PROCEDIMENT TO WWW_${projectnameuppercase};
    GRANT SELECT, INSERT, UPDATE, DELETE ON ${prefixuppercase}_UNITATORGANICA TO WWW_${projectnameuppercase};


