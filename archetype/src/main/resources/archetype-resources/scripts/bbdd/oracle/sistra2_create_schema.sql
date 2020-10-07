#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

    create sequence ${prefixuppercase}_ANOTACIOINBOX_SEQ start with 1 increment by  50;

    create table ${prefixuppercase}_ANOTACIOINBOX (
        ID number(19,0) not null,
        IDENTIFICADOR varchar2(100 char) not null,
        CLAUACCES varchar2(44 char) not null,
        CONTINGUT clob,
        CREATED timestamp not null,
        ESTAT varchar2(255 char),
        EXPEDIENT varchar2(256 char)
    );

    create index ${prefixuppercase}_ANOTACIOINBOX_PK_I on ${prefixuppercase}_ANOTACIOINBOX (ID);

    alter table ${prefixuppercase}_ANOTACIOINBOX
        add constraint ${prefixuppercase}_ANOTACIOINBOX_PK primary key (ID);

    -- Grants per l'usuari www_${rootArtifactId}
    -- seqüències
    GRANT SELECT, ALTER ON ${prefixuppercase}_ANOTACIOINBOX_SEQ TO WWW_${projectnameuppercase};
    -- taules
    GRANT SELECT, INSERT, UPDATE, DELETE ON ${prefixuppercase}_ANOTACIOINBOX TO WWW_${projectnameuppercase};


