package GUI.bundles;

import java.util.ListResourceBundle;

public class Exceptions_en extends ListResourceBundle {
    private Object[][] contents = {{"emptyException", "can't be empty"},
            {"xValueException", "value of x <= 201"},
            {"yValueException", "value of y > -440"},
            {"healthValueException", "value of health > 0"},
            {"heartCountValueException", "value of 0 < heart count <= 3"},
            {"marinesCountValueException", "value of 0 < marines count <= 1000"},
            {"intParseException", "must be int"},
            {"doubleParseException", "must be double"},
            {"longParseException", "must be long"},
            {"orEmpty", " or empty"},
            {"formatException", "Only word characters [a-zA-Z_0-9]."},
            {"PasswordNotMatchException", "passwords doesn't match"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
