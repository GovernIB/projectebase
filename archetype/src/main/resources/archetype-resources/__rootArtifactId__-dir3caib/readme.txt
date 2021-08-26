#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

Mòdul d'exemple per realitzar cerques d'organismes amb Dir3CAIB 1.0 emprant l'api descrita a:

    https://github.com/GovernIB/dir3caib/blob/dir3caib-1.0/doc/pdf/Manual_de_WebServices_i_APIREST_de_DIR3CAIB.pdf

Per provar el plugin:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar el fitxer scripts/configuracio/${rootArtifactId}.properties indicant les propietats necessàries
        per la connexió  a la secció del mòdul dir3caib.
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

    3. Desplegar l'aplicació dins l'EAR

        Desplegar normalment l'EAR executant dins la carpeta ARREL:

            mvn verify cargo:deploy

        El mòdul estarà accessible a:

            http://localhost:8080/${rootArtifactId}/dir3caib

L'aplicació bàsicament permet fer coses indicar una sèrie de filtres per obtenir una llista d'organismes.