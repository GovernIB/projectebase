
    create sequence PBS_PROCEDIMENT_SEQ start 1 increment 1;
    create sequence PBS_UNITATORGANICA_SEQ start 1 increment 1;

    create table PBS_PROCEDIMENT (
       ID int8 not null,
        CODISIA varchar(8) not null,
        NOM varchar(50) not null,
        UNITATORGANICAID int8 not null,
        constraint PBS_PROCEDIMENT_PK primary key (ID)
    );

    create table PBS_UNITATORGANICA (
       ID int8 not null,
        CODIDIR3 varchar(9) not null,
        DATACREACIO date not null,
        ESTAT int4 not null,
        NOM varchar(50) not null,
        constraint PBS_UNITAT_PK primary key (ID)
    );

    create index PBS_PROCEDIMENT_PK_I on PBS_PROCEDIMENT (ID);
    create index PBS_PROCEDIMENT_CODISIA_UK_I on PBS_PROCEDIMENT (CODISIA);
    create index PBS_PROCEDIMENT_UNITAT_FK_I on PBS_PROCEDIMENT (UNITATORGANICAID);

    alter table PBS_PROCEDIMENT 
       add constraint PBS_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    create index PBS_UNITAT_PK_I on PBS_UNITATORGANICA (ID);
    create index PBS_UNITAT_CODIDIR3_UK_I on PBS_UNITATORGANICA (CODIDIR3);

    alter table PBS_UNITATORGANICA 
       add constraint PBS_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table PBS_PROCEDIMENT 
       add constraint PBS_PROCEDIMENT_UNITAT_FK 
       foreign key (UNITATORGANICAID) 
       references PBS_UNITATORGANICA;
