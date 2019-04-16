package javax0.util.string;

import javax0.geci.engine.Geci;
import javax0.geci.jamal.JamalGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static javax0.geci.api.Source.maven;

public class Generate {

    @Test
    void generateCode() throws Exception {
        Assertions.assertFalse(
                new Geci().source(maven().mainSource()).only("Str.java").register(new JamalGenerator()).generate(),
                Geci.FAILED
        );
    }
}
