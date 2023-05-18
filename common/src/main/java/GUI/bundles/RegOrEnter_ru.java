package GUI.bundles;

import java.util.ListResourceBundle;

public class RegOrEnter_ru extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "назад"},
            {"signUpButton", "регистрация"},
            {"signInButton", "вход"},
            {"field", "Напишите "},
            {"loginName", "логин"},
            {"passwordName", "пароль"},
            {"passwordAgainName", "пароль опять"},
            {"welcomeMsg", "Присоединяйся"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
