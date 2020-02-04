#set( $symbol_pount = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )
REM Si no volem LOBs en un altre tablespace llavors afegir la següent
REM linia despres de mvn: -Dsqlgenerator.oracle.generatelob=false
mvn exec:java -Dsqlgenerator.oracle.generatelob=false -Dsqlgenerator.project.name=${rootArtifactId} -Dexec.mainClass="${package}.sqlgenerator.SqlGenerator" -Dexec.args="${rootArtifactId}PU %1%"

REM mvn package -Pgensql
