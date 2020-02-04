package es.caib.projectebase.sqlgenerator;

import java.io.File;
import java.net.URI;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Action;
import org.hibernate.tool.schema.TargetType;

/**
 * 
 * @author anadal
 *
 */
public class SchemaGenerator {

    private final Configuration cfg;

    private final List<Class<?>> classes = new ArrayList<Class<?>>();

    @SuppressWarnings("rawtypes")
    public SchemaGenerator(String[] packagesName) throws Exception {
        cfg = new Configuration();
        cfg.setProperty("hibernate.hbm2ddl.auto", "create");

        for (String packageName : packagesName) {
            List<Class<?>> cls = collectClasses(packageName);
            classes.addAll(cls);

            for (Class clazz : cls) {
                cfg.addAnnotatedClass(clazz);
            }
        }
    }

    private ClassLoader getClassLoader() throws ClassNotFoundException {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new ClassNotFoundException("Can't get class loader.");
        }
        return cld;
    }

    private List<Class<?>> collectClasses(String packageName) throws Exception {

        List<Class<?>> classes = new ArrayList<Class<?>>();

        {
            ClassLoader cld = getClassLoader();
            URI uri = cld.getResource(packageName.replace('.', '/')).toURI();
            Path myPath;
            if (uri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object> emptyMap());
                myPath = fileSystem.getPath(packageName.replace('.', '/'));
            } else {
                myPath = Paths.get(uri);
            }
            Stream<Path> walk = Files.walk(myPath, 1);
            for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
                Path p = it.next();
                String file = p.getFileName().toString();
                if (file.endsWith(".class")) {
                    String name = packageName + '.' + file.substring(0, file.length() - 6);
                    classes.add(Class.forName(name));
                }
            }
            walk.close();

        }

        return classes;
    }

    public void generate(String dialect, File[] files, Action[] actions) throws Exception {

        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder().applySetting("hibernate.dialect", dialect).build());

        for (Class<?> clazz : classes) {
            System.out.println("Class: " + clazz);
            metadata.addAnnotatedClass(clazz);
        }

        SchemaExport export = new SchemaExport();

        export.setDelimiter(";");

        export.setFormat(true);

        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.SCRIPT);

        for (int i = 0; i < actions.length; i++) {
            export.setOutputFile(files[i].getAbsolutePath());
            export.execute(targetTypes, actions[i], (MetadataImplementor) metadata.buildMetadata());
        }

    }

}
