#set( $symbol_pount = '#' )
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


 -- INICI Indexes
create index ${prefixuppercase}_PROCEDIMENT_PK_I on ${prefixuppercase}_PROCEDIMENT (ID);
create index ${prefixuppercase}_PROCEDIMENT_UNITAT_FK_I on ${prefixuppercase}_PROCEDIMENT (UNITATORGANICAID);
create index ${prefixuppercase}_UNITAT_CODIDIR3_UK_I on ${prefixuppercase}_UNITATORGANICA (CODIDIR3);
 -- FINAL Indexes

 -- INICI PK's
    alter table ${prefixuppercase}_PROCEDIMENT add constraint ${prefixuppercase}_PROCEDIMENT_pk primary key (ID);

    alter table ${prefixuppercase}_UNITATORGANICA add constraint ${prefixuppercase}_UNITATORGANICA_pk primary key (ID);

 -- FINAL PK's

 -- INICI FK's

    alter table ${prefixuppercase}_PROCEDIMENT 
       add constraint ${prefixuppercase}_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    alter table ${prefixuppercase}_UNITATORGANICA 
       add constraint ${prefixuppercase}_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table ${prefixuppercase}_PROCEDIMENT 
       add constraint ${prefixuppercase}_PROCEDIMENT_UNITAT_FK 
       foreign key (UNITATORGANICAID) 
       references ${prefixuppercase}_UNITATORGANICA;
 -- FINAL FK's

