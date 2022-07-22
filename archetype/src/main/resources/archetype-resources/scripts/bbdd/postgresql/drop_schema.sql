#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

    alter table ${prefixuppercase}_PROCEDIMENT 
       drop constraint ${prefixuppercase}_PROCEDIMENT_UNITAT_FK;

    drop table if exists ${prefixuppercase}_PROCEDIMENT cascade;

    drop table if exists ${prefixuppercase}_UNITATORGANICA cascade;

    drop sequence if exists ${prefixuppercase}_PROCEDIMENT_SEQ;

    drop sequence if exists ${prefixuppercase}_UNITATORGANICA_SEQ;
