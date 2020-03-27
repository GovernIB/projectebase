#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

    create sequence ${prefixuppercase}_PROCEDIMENT_SEQ start 1 increment 1;
    create sequence ${prefixuppercase}_UNITATORGANICA_SEQ start 1 increment 1;

    create table ${prefixuppercase}_PROCEDIMENT (
       ID int8 not null,
        CODISIA varchar(8) not null,
        NOM varchar(50) not null,
        UNITATORGANICAID int8 not null,
        constraint ${prefixuppercase}_PROCEDIMENT_PK primary key (ID)
    );

    create table ${prefixuppercase}_UNITATORGANICA (
       ID int8 not null,
        CODIDIR3 varchar(9) not null,
        DATACREACIO date not null,
        ESTAT int4 not null,
        NOM varchar(50) not null,
        constraint ${prefixuppercase}_UNITAT_PK primary key (ID)
    );

    create index ${prefixuppercase}_PROCEDIMENT_PK_I on ${prefixuppercase}_PROCEDIMENT (ID);
    create index ${prefixuppercase}_PROCEDIMENT_CODISIA_UK_I on ${prefixuppercase}_PROCEDIMENT (CODISIA);
    create index ${prefixuppercase}_PROCEDIMENT_UNITAT_FK_I on ${prefixuppercase}_PROCEDIMENT (UNITATORGANICAID);

    alter table ${prefixuppercase}_PROCEDIMENT 
       add constraint ${prefixuppercase}_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    create index ${prefixuppercase}_UNITAT_PK_I on ${prefixuppercase}_UNITATORGANICA (ID);
    create index ${prefixuppercase}_UNITAT_CODIDIR3_UK_I on ${prefixuppercase}_UNITATORGANICA (CODIDIR3);

    alter table ${prefixuppercase}_UNITATORGANICA 
       add constraint ${prefixuppercase}_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table ${prefixuppercase}_PROCEDIMENT 
       add constraint ${prefixuppercase}_PROCEDIMENT_UNITAT_FK 
       foreign key (UNITATORGANICAID) 
       references ${prefixuppercase}_UNITATORGANICA;
