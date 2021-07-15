
Mòdul d'exemple de web pública FRONT integrada amb LoginIB

Per provar la integració amb LOGINIB:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar els fitxers de configuració:
        - scripts/configuracio/projectebase.system.properties
        - scripts/configuracio/projectebase.properties

        Indicant les dades necessaries per la connexió a les propietats que estan a la secció del mòdul FRONT que
        fan referència a LoginIB.

        Serà necessari que l'usuari emprat a la integració tengui el rol LIB_API
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

        Assegurar-se que s'han seguit les passes descrites a scripts/configuracio/readme.txt perquè els fitxers
      es puguin carregar des de JBoss.

    3. Desplegar l'aplicació dins l'EAR

        Desplegar normalment l'EAR executant dins la carpeta ARREL del projecte:

            mvn verify cargo:deploy

        El mòdul estarà accessible a:

            http://localhost:8080/projectebasefront
            https://localhost:8443/projectebasefront

L'aplicació bàsicament permet securitzar una part d'una aplicació requerint l'autenticacó amb LoginIB

 - Les URLs a les que s'aplica el filtre "LoginIBFilter" (per defecte /secured/*) estaran protegides.
 - Mitjançant el "LoginIBModel" (o dins EL ${loginib}), es pot consultar si l'usuari està autenticat
 i les dades d'autenticació
 - Veure "/WEB-INF/includes/logindata.jsp" per veure com es pot emprar la informació anterior.
 - El servlet "/logout" s'encarrega d'iniciar el logout, que retornarà a la pàgina principal
