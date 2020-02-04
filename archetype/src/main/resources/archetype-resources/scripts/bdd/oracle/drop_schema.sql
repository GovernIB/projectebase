#set( $symbol_pount = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

    drop table ${prefixuppercase}_PROCEDIMENT cascade constraints;

    drop table ${prefixuppercase}_UNITATORGANICA cascade constraints;

    drop sequence ${prefixuppercase}_PROCEDIMENT_SEQ;

    drop sequence ${prefixuppercase}_UNITATORGANICA_SEQ;
