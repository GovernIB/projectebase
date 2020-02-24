#set( $symbol_pount = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )
Es recomana crear un schema amb el mateix nom d'usuari:

   CREATE SCHEMA ${rootArtifactId} AUTHORIZATION ${rootArtifactId};

Veure: https://www.postgresql.org/docs/10/ddl-schemas.html
