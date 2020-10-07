
    create sequence PBS_ANOTACIOINBOX_SEQ start 1 increment 50;

    create table PBS_ANOTACIOINBOX (
       ID int8 not null,
        CLAUACCES varchar(44) not null,
        CONTINGUT text,
        CREATED timestamp not null,
        ESTAT varchar(255),
        EXPEDIENT varchar(256),
        IDENTIFICADOR varchar(100) not null,
        constraint PBS_ANOTACIOINBOX_PK primary key (ID)
    );

    create index PBS_ANOTACIOINBOX_PK_I on PBS_ANOTACIOINBOX (ID);
