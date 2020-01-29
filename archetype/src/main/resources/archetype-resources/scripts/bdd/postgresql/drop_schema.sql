#set( $symbol_pount = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

-- ALERTA!!!!
-- Emprat per esborrar tota la base de dades

alter table if exists ${prefixuppercase}_PROCEDIMENT drop constraint if exists ${prefixuppercase}_PROCEDIMENT_UNITAT_FK;

drop table if exists ${prefixuppercase}_PROCEDIMENT cascade;
drop table if exists ${prefixuppercase}_UNITATORGANICA cascade;

drop sequence if exists ${prefixuppercase}_PROCEDIMENT_SEQ;
drop sequence if exists ${prefixuppercase}_UNITATORGANICA_SEQ;
