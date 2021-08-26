#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

Mòdul d'exemple per realitzar consultes al Servei de Verificació de Dades d'Identitat emprant el client PINBAL definit a:

    https://github.com/GovernIB/pinbal/blob/pinbal-1.4/pinbal-client/

Per provar el plugin:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar els fitxers de configuració:
        - scripts/configuracio/${rootArtifactId}.system.properties
        - scripts/configuracio/${rootArtifactId}.properties

        Indicant les dades necessaries per la connexió a les propietats que estan a la secció del mòdul PINBAL.
        Serà necessari que l'usuari emprat a la integració tengui el rol PBL_WS
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

        Assegurar-se que s'han seguit les passes descrites a scripts/configuracio/readme.txt perquè els fitxers
      es puguin carregar des de JBoss.

    3. Desplegar l'aplicació dins l'EAR

        Desplegar normalment l'EAR executant dins la carpeta ARREL:

            mvn verify cargo:deploy

        El mòdul estarà accessible a:

            http://localhost:8080/${rootArtifactId}/pinbal

L'aplicació bàsicament permet realitzar una consulta al Servei de Verificació de Dades d'Intentitat, emplenant la
documentació (DNI o NIE), i opcionalment el nom, primer cognom o segon cognom. L'aplicació realitza una consulta per
verificar si el número de document és vàlid i si es correspon amb el nom i/o cognoms indicats.
També permet descarregar el justificant de la consulta.