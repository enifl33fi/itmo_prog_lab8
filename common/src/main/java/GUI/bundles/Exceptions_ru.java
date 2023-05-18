package GUI.bundles;

import java.util.ListResourceBundle;

public class Exceptions_ru  extends ListResourceBundle {
    private Object[][] contents = {{"emptyException", "не может быть пустым"},
            {"xValueException", "значение x <= 201"},
            {"yValueException", "значение y > -440"},
            {"healthValueException", "значение здоровья > 0"},
            {"heartCountValueException", "значение 0 < количества здоровья <= 3"},
            {"marinesCountValueException", "значение 0 < количества десантников <= 1000"},
            {"intParseException", "должен быть int"},
            {"doubleParseException", "должен быть double"},
            {"longParseException", "должен быть long"},
            {"orEmpty", " или пустым"},
            {"formatException", "Только символы [a-zA-Z_0-9]."},
            {"PasswordNotMatchException", "пароли не совпадают"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
