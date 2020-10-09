#set( $symbol_pound = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

El contingut adaptat del fitxer de properties s'ha de copiar dins el servidor i referenciar-lo
amb la propietat de sistema "${package}.properties".

Això és pot fer a dins el fitxer de configuració
    JBOSS_HOME${symbol_escape}standalone${symbol_escape}configuration${symbol_escape}standalone.xml o equivalent
a dins l'apartat de <system-properties>:

    ...
    <system-properties>
        <property name="${package}.properties" value="/ruta/cap/al/fitxer/${rootArtifactId}.properties"/>
    </system-properties>
    ...
