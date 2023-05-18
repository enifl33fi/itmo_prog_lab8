package GUI.bundles;

import java.util.ListResourceBundle;

public class Exceptions_es_SV extends ListResourceBundle {
    private Object[][] contents = {{"emptyException", "no puede estar vacío"},
            {"xValueException", "valor x <= 201"},
            {"yValueException", "valor y > -440"},
            {"healthValueException", "valor salud > 0"},
            {"heartCountValueException", "valor 0 < cantidades de salud <= 3"},
            {"marinesCountValueException", "valor 0 < el número de paracaidistas <= 1000"},
            {"intParseException", "debe ser int"},
            {"doubleParseException", "debe ser double"},
            {"longParseException", "debe serь long"},
            {"orEmpty", " o vacío"},
            {"formatException", "Sólo símbolos [a-zA-Z_0-9]."},
            {"PasswordNotMatchException", "las contraseñas no coinciden"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}