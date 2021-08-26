
Mòdul d'exemple per conectar amb l'Arxiu Digital emprant el plugin d'arxiu disponible a:
https://github.com/GovernIB/pluginsib-arxiu

Per provar el plugin:

    1. Fixar variable d'entorn JBOSS_HOME

    2. Editar el fitxer scripts/configuracio/projectebase.properties indicant les propietats necessàries
    per la connexió  a la secció del mòdul d'arxiu.
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

    3. Desplegar l'aplicació dins l'EAR

        Desplegar normalment l'EAR executant dins la carpeta arrel:

            mvn verify cargo:deploy

        El mòdul estarà accessible a:

            http://localhost:8080/projectebase/arxiu

L'aplicació bàsicament permet indicar un nom d'expedient i un fitxer. Crearà un expedient dins l'arxiu amb el nom
indicat i la data actual i inserirà un document amb el contingut del fitxer i una sèrie de metadades prefixades.

També visualitza els expedients que s'han creat durant la sessió d'usuari, i esborrar-los.