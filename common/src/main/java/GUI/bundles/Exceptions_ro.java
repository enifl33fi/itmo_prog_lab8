package GUI.bundles;

import java.util.ListResourceBundle;

public class Exceptions_ro extends ListResourceBundle {
    private Object[][] contents = {{"emptyException", "nu poate fi gol"},
            {"xValueException", "valoarea x <= 201"},
            {"yValueException", "valoarea y > -440"},
            {"healthValueException", "health value > 0"},
            {"heartCountValueException", "значение 0 < valoare sănătate <= 3"},
            {"marinesCountValueException", "значение 0 < numărul de pușcași marini <= 3"},
            {"intParseException", "trebuie să fie int"},
            {"doubleParseException", "trebuie să fie double"},
            {"longParseException", "trebuie să fie long"},
            {"orEmpty", " or empty"},
            {"formatException", "Numai caractere [a-zA-Z_0-9]."},
            {"PasswordNotMatchException", "parolele nu se potrivesc"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}