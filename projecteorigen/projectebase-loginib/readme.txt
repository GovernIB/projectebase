
Mòdul d'exemple per per integrar un projecte amb LOGINIB

    https://github.com/GovernIB/loginib.......

Per provar el plugin:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar els fitxers de configuració:
        - scripts/configuracio/projectebase.system.properties
        - scripts/configuracio/projectebase.properties

        Indicant les dades necessaries per la connexió a les propietats que estan a la secció del mòdul LOGINIB.
        Serà necessari que l'usuari emprat a la integració tengui el rol LIB_API
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

        Assegurar-se que s'han seguit les passes descrites a scripts/configuracio/readme.txt perquè els fitxers
      es puguin carregar des de JBoss.

    3. Desplegar l'aplicació dins l'EAR

        Desplegar normalment l'EAR executant dins la carpeta ARREL del projecte:

            mvn verify cargo:deploy

        El mòdul estarà accessible a:

            http://localhost:8080/projectebase/loginib

L'aplicació bàsicament permet ....