package experiment.utils;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileUtils;

import java.io.InputStream;

public class JenaTestUtils {

    public static Model laodModelFromResource(String path) {
        InputStream inputStream = JenaTestUtils.class.getResourceAsStream(path);
        if (inputStream == null) {
            throw new IllegalArgumentException("Cannot find resource with path \"" + path + "\".");
        }

        Model model = ModelFactory.createDefaultModel();

        model.read(inputStream, null, FileUtils.langTurtle);

        return model;
    }
}
