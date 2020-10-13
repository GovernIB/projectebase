
Mòdul d'exemple per conectar amb REGISTRE emprant l'api descrita a:

    https://github.com/GovernIB/registre/blob/registre-3.1/doc/pdf/Manual_Integracio_RegWeb3.pdf

Per provar el plugin:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar el fitxer scripts/configuracio/projectebase.properties indicant les propietats necessàries
      per la connexió  a la secció del mòdul Registre
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

    3. Desplegar l'aplicació dins l'EAR

        Desplegar normalment l'EAR executant dins la carpeta arrel del projecte:

            mvn verify cargo:deploy

        El mòdul estarà accessible a:

            http://localhost:8080/projectebase/registre


L'aplicació bàsicament permet indicar un assumte i un fitxer. Crearà un assentament registral d'entrada a l'oficina
 indicada a la configuració amb l'assumpte indicat, i una sèrie de dades prefixades. El document si s'indica
 s'afegirà a l'assentament com annex.

També visualitza els assentaments regsitrals que s'han creat durant la sessió d'usuari, i permet descarregar el
justificant de la presentació del registre.