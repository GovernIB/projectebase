
# Compara ignorat target de maven, i els fitxers de projecte de Eclipse, IntelliJ i NetBeans
diff -r -q -x target -x .classpath -x .project -x .factorypath -x .settings -x .idea -x *.iml -x nb-configuration.xml ../projecteorigen projectebase
