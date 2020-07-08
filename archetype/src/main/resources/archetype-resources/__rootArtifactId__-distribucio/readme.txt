#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

Mòdul d'exemple per "FER COSES" amb Distribució 0.9 emprant l'api descrita a:

    https://github.com/GovernIB/distribucio

Per provar el plugin:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar el fitxer src/main/resources/distribucio/Distribucio.properties indicant les propietats necessàries per la connexió
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

    3. Desplegar l'aplicació dins l'EAR o en solitari

        3.1. Dins l'EAR

            Desplegar normalment l'EAR executant dins la carpeta ARREL del projecte:
                mvn verify cargo:deploy
            El mòdul estarà accessible a:
                http://localhost:8080/${rootArtifactId}/distribucio

        3.2. Desplegament en solitari.

            Dins AQUESTA carpeta executar:
                mvn -Pdistribucio-war-deploy verify cargo:deploy
            El mòdul estarà accessible a:
                http://localhost:8080/${rootArtifactId}-distribucio


L'aplicació bàsicament permet fer coses.......

També permet fer altres coses......