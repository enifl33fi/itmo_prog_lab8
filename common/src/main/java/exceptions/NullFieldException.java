package exceptions;

import java.util.ResourceBundle;

/**
 * Thrown when field that shouldn't be null is null.
 * @author Kirill Markov
 * @version 1.0 */
public class NullFieldException extends RuntimeException {
    public NullFieldException(String field) {
        super(String.format("%s %s",field, ResourceBundle.getBundle("GUI.bundles.Exceptions").getString("emptyException")));
    }
}
