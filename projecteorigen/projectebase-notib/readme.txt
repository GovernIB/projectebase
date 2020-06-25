
Mòdul d'exemple per crear notificacions amb NOTIB 1.1 (actualment 1.1.2) emprant l'api descrita a:

    https://github.com/GovernIB/notib/raw/notib-1.1/doc/pdf/NOTIB_integracio.pdf

Per provar el plugin:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar el fitxer src/main/resources/notib/Notib.properties indicant les propietats necessàries per la connexió
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

    3. Desplegar l'aplicació dins l'EAR o en solitari

        3.1. Dins l'EAR

            Desplegar normalment l'EAR executant dins la carpeta arrel del projecte:
                mvn verify cargo:deploy
            El mòdul estarà accessible a:
                http://localhost:8080/projectebase/notib

        3.2. Desplegament en solitari.

            En aquesta carpeta executar:
                mvn -Pnotib-war-deploy verify cargo:deploy
            El mòdul estarà accessible a:
                http://localhost:8080/projectebase-notib


L'aplicació bàsicament permet indicar les dades necessaries de la notificació, amb un fitxer de notificació
adjunt, així com al titular de la notificació, i fer un enviament de la notificació que crearà un registre de
sortida i el posarà a disposició del titular a la carpeta ciutadana.

També visualitza les notificacions que s'han creat durant la sessió d'usuari, i permet actualitzar-ne l'estat,
així com davallar les certificacions dels enviaments una vegada s'han produït.
