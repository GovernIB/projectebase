#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

    create sequence ${prefixuppercase}_ANOTACIOINBOX_SEQ start 1 increment 50;

    create table ${prefixuppercase}_ANOTACIOINBOX (
       ID int8 not null,
        CLAUACCES varchar(44) not null,
        CONTINGUT text,
        CREATED timestamp not null,
        ESTAT varchar(255),
        EXPEDIENT varchar(256),
        IDENTIFICADOR varchar(100) not null,
        constraint ${prefixuppercase}_ANOTACIOINBOX_PK primary key (ID)
    );

    create index ${prefixuppercase}_ANOTACIOINBOX_PK_I on ${prefixuppercase}_ANOTACIOINBOX (ID);
