package experiment.utils;

import cz.cvut.kbss.jopa.Persistence;
import cz.cvut.kbss.jopa.model.*;
import cz.cvut.kbss.ontodriver.jena.config.JenaOntoDriverProperties;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;

import java.util.HashMap;
import java.util.Map;

public class JopaPersistenceUtils {

    public static EntityManagerFactory createEntityManagerFactoryWithMemoryStore(String packageToScan) {

        Map<String, String> persistenceProperties = new HashMap<>();
        persistenceProperties.put(JOPAPersistenceProperties.ONTOLOGY_PHYSICAL_URI_KEY, "local://temporary");
        persistenceProperties.put(JOPAPersistenceProperties.DATA_SOURCE_CLASS, "cz.cvut.kbss.ontodriver.jena.JenaDataSource");
        persistenceProperties.put(JOPAPersistenceProperties.LANG, "en");
        persistenceProperties.put(JOPAPersistenceProperties.SCAN_PACKAGE, packageToScan);
        persistenceProperties.put(PersistenceProperties.JPA_PERSISTENCE_PROVIDER, JOPAPersistenceProvider.class.getName());


        persistenceProperties.put(JenaOntoDriverProperties.IN_MEMORY, "true");

        return Persistence.createEntityManagerFactory("testPersistenceUnit", persistenceProperties);
    }


    public static Dataset getDataset(EntityManager entityManager) {
        return entityManager.unwrap(Dataset.class);
    }

    public static EntityManager getEntityManager(String packageToScan, Model model) {


        EntityManagerFactory emf = JopaPersistenceUtils.createEntityManagerFactoryWithMemoryStore(packageToScan);
        EntityManager em = emf.createEntityManager();

        JopaPersistenceUtils.getDataset(em).setDefaultModel(model);
        return em;
    }
}