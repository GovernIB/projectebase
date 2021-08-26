#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

El contingut adaptat del fitxer dins la subcarpeta oracle o postgresql es pot copiar
a dins el fitxer de configuració
    JBOSS_HOME${symbol_escape}standalone${symbol_escape}configuration${symbol_escape}standalone.xml o equivalent
Concretament cal copiar a partir del tag <datasource> del fitxer "-ds.xml" a dins el tag
de <datasources> del fitxer de configuració standalone.xml o equivalent.

També es pot copiar directament dins la carpeta  JBOSS_HOME${symbol_escape}standalone${symbol_escape}deployments.

Als quaderns de carga s'haurà d'enviar el fitxer renombrat com a ${rootArtifactId}-ds.xml.