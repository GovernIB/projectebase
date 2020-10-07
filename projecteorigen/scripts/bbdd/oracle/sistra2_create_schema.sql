
    create sequence PBS_ANOTACIOINBOX_SEQ start with 1 increment by  50;

    create table PBS_ANOTACIOINBOX (
        ID number(19,0) not null,
        IDENTIFICADOR varchar2(100 char) not null,
        CLAUACCES varchar2(44 char) not null,
        CONTINGUT clob,
        CREATED timestamp not null,
        ESTAT varchar2(255 char),
        EXPEDIENT varchar2(256 char)
    );

    create index PBS_ANOTACIOINBOX_PK_I on PBS_ANOTACIOINBOX (ID);

    alter table PBS_ANOTACIOINBOX
        add constraint PBS_ANOTACIOINBOX_PK primary key (ID);

    -- Grants per l'usuari www_projectebase
    -- seqüències
    GRANT SELECT, ALTER ON PBS_ANOTACIOINBOX_SEQ TO WWW_PROJECTEBASE;
    -- taules
    GRANT SELECT, INSERT, UPDATE, DELETE ON PBS_ANOTACIOINBOX TO WWW_PROJECTEBASE;


