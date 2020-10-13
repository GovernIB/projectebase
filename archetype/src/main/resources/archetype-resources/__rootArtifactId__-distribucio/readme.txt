#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

Mòdul d'exemple per enviar anotacvions amb l'API de Bústica de Distribució 0.9 emprant l'api descrita a:

    https://github.com/GovernIB/distribucio

Per provar el plugin:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar el fitxer scripts/configuracio/${rootArtifactId}.properties indicant les propietats necessàries
        per la connexió  a la secció del mòdul Distribució
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

    3. Desplegar l'aplicació dins l'EAR

        Desplegar normalment l'EAR executant dins la carpeta ARREL del projecte:

            mvn verify cargo:deploy

        El mòdul estarà accessible a:

            http://localhost:8080/${rootArtifactId}/distribucio

L'aplicació bàsicament permet introduir les dades bàsiques d'una anotació amb un interessat i un annex.