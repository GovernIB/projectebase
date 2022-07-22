#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

    drop table if exists ${prefixuppercase}_ANOTACIOINBOX cascade;

    drop sequence if exists ${prefixuppercase}_ANOTACIOINBOX_SEQ;
