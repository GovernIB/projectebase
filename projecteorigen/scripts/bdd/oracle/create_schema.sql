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


 -- INICI Indexes
create index PBS_PROCEDIMENT_PK_I on PBS_PROCEDIMENT (ID);
create index PBS_PROCEDIMENT_UNITAT_FK_I on PBS_PROCEDIMENT (UNITATORGANICAID);
create index PBS_UNITAT_CODIDIR3_UK_I on PBS_UNITATORGANICA (CODIDIR3);
 -- FINAL Indexes

 -- INICI PK's
    alter table PBS_PROCEDIMENT add constraint PBS_PROCEDIMENT_pk primary key (ID);

    alter table PBS_UNITATORGANICA add constraint PBS_UNITATORGANICA_pk primary key (ID);

 -- FINAL PK's

 -- INICI FK's

    alter table PBS_PROCEDIMENT 
       add constraint PBS_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    alter table PBS_UNITATORGANICA 
       add constraint PBS_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table PBS_PROCEDIMENT 
       add constraint PBS_PROCEDIMENT_UNITAT_FK 
       foreign key (UNITATORGANICAID) 
       references PBS_UNITATORGANICA;
 -- FINAL FK's

