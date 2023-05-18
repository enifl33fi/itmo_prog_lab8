package GUI.bundles;

import java.util.ListResourceBundle;

public class Exceptions_it extends ListResourceBundle {
    private Object[][] contents = {{"emptyException", "non può essere vuoto"},
            {"xValueException", "valore x <= 201"},
            {"yValueException", "valore y > -440"},
            {"healthValueException", "valore salute > 0"},
            {"heartCountValueException", "valore 0 < quantità di salute <= 3"},
            {"marinesCountValueException", "valore 0 < il numero di paracadutisti <= 1000"},
            {"intParseException", "deve essere int"},
            {"doubleParseException", "deve essere double"},
            {"longParseException", "deve essere long"},
            {"orEmpty", " o vuoto"},
            {"formatException", "Solo simboli [a-zA-Z_0-9]."},
            {"PasswordNotMatchException", "le password non corrispondono"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
