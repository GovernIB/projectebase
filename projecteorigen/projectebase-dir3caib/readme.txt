
Mòdul d'exemple per realitzar cerques d'organismes amb Dir3CAIB 1.0 emprant l'api descrita a:

    https://github.com/GovernIB/dir3caib/blob/dir3caib-1.0/doc/pdf/Manual_de_WebServices_i_APIREST_de_DIR3CAIB.pdf

Per provar el plugin:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar el fitxer src/main/resources/dir3caib/Dir3Caib.properties indicant les propietats necessàries per la connexió
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

    3. Desplegar l'aplicació dins l'EAR o en solitari

        3.1. Dins l'EAR

            Desplegar normalment l'EAR executant dins la carpeta ARREL del projecte:
                mvn verify cargo:deploy
            El mòdul estarà accessible a:
                http://localhost:8080/projectebase/dir3caib

        3.2. Desplegament en solitari.

            Dins AQUESTA carpeta executar:
                mvn -Pdir3caib-war-deploy verify cargo:deploy
            El mòdul estarà accessible a:
                http://localhost:8080/projectebase-dir3caib


L'aplicació bàsicament permet fer coses indicar una sèrie de filtres per obtenir una llista d'organismes.