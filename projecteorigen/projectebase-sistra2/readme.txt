
Mòdul d'exemple d'implementació d'un Backoffice per rebre tràmits de SISTRA2 a través de Distribució:

    https://github.com/GovernIB/distribucio

Per provar el plugin:

    1. fixar variable d'entorn JBOSS_HOME

    2. Editar el fitxer scripts/configuracio/projectebase.properties indicant les propietats necessàries
      per la connexió amb distribució i arxiu
      (tenir en compte que si s'accedeix a un servidor https pot necessitar importar el certificat dins el JDK)

    3. Executar l'script "sistra2_create_schema.sql" dins la mateixa base de dades de projectebase.

    4. Desplegar l'aplicació dins l'EAR

        Desplegar normalment l'EAR executant dins la carpeta ARREL del projecte:

            mvn verify cargo:deploy

        El WS per emprar a la regla corresponent de Distribució seria la següent:

            http://localhost:8080/projectebase-sistra2/BackofficeService/BackofficeServicePort

        Dins el mòdul projectebase-api s'inclou un Domini SISTRA2 que retorna les unitats adminsitratives actives
        creades dins projectebase-back.

        Per provar el domini dins SISTRA2 cal emprar la URL:

            http://localhost:8080/projectebase/api/services/sistra2/dominis/unitats

        i emprar opcionalment el paràmetre de filtre: "codiDir3"

El funcionament del mòdul és el següent:

 - A través del WS indicat donat d'alta a Distribució reb els avisos de noves anotacions d'entrada i les emmagatzema
  a la taula PBS_ANOTACIOINBOX amb estat PENDENT

 - Mitjançant un procés períodic processa les noves anotacions PENDENT i per cada una obté la informació completa de
 l'anotació consultant al WS de distribució. Si la consulta té èxit mitjançant el WS de distribució canvia l'estat a
 REBUDA i també el canvia dins la base de dades.

 - Un segon procés períodic mira les anotacions REBUDA, i per cada una, mitjançant les eines de backoffice de distribució
 i el plugin d'arxiu, creen un expedient dins arxiu amb tots els annexos rebuts. Si l'operació tè èxit, guarda dins la
 taula PBS_ANOTACIINBOX l'identificador de l'expedient i marca l'estat a PROCESSADA amb el WS de distribució i també
 dins la base de dades, mentre que si les operacions amb l'arxiu donen un error, marca l'estat a ERROR.

 Si es produeix un error quan s'envia el canvi d'estat al WS de distribució, no es realitza el canvi d'estat a la base
 de dades, per tant es tornarà a processar pel procés períodic.