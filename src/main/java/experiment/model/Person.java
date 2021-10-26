
package experiment.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLAnnotationProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.vocabulary.RDFS;


@OWLClass(iri = "http://onto.fel.cvut.cz/ontologies/experiment/Person")
public class Person
{

    @Id(generated = true)
    protected String id;

    @OWLAnnotationProperty(iri = RDFS.LABEL)
    protected String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
