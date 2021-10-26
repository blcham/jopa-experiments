package experiment;

import cz.cvut.kbss.jopa.model.EntityManager;
import experiment.model.Person;
import experiment.utils.JenaTestUtils;
import experiment.utils.JopaPersistenceUtils;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.util.FileUtils;
import org.apache.jena.vocabulary.RDFS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExperimentTest {

    private static final String MB_IRI = "http://onto.fel.cvut.cz/ontologies/experiment/miro-blasko";

    @Test
    public void propertyIsExtractedWithoutLanguageTag() {
        Model inputModel = JenaTestUtils.laodModelFromResource("/experiment.ttl");

        EntityManager em = JopaPersistenceUtils.getEntityManager("experiment.model", inputModel);

        Person mb = em.find(Person.class, MB_IRI);

        Literal label = inputModel.getProperty(
                ResourceFactory.createResource(MB_IRI),
                RDFS.label
        ).getObject().asLiteral();

        assertEquals(label, ResourceFactory.createLangLiteral("Miroslav Blasko", "en"));
        assertEquals(mb.getLabel(), "Miroslav Blasko");
    }


    private void saveNewPerson(EntityManager em) {
        em.getTransaction().begin();

        Person mb = new Person();
        mb.setId("http://onto.fel.cvut.cz/ontologies/experiment/miro-blasko");
        mb.setLabel("Miroslav Blasko");

        em.persist(mb);

        em.getTransaction().commit();
    }

    private void printOutputModel(EntityManager em) {
        Model outputModel = JopaPersistenceUtils.getDataset(em).getDefaultModel();
        outputModel.write(System.out, FileUtils.langTurtle, null);
    }

}
