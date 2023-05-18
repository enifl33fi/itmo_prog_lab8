package GUI.bundles;

import java.util.ListResourceBundle;

public class RegOrEnter_ro extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "spate"},
            {"signUpButton", "înregistrare"},
            {"signInButton", "conectați-vă"},
            {"field", "Scrieți "},
            {"loginName", "login"},
            {"passwordName", "parola"},
            {"passwordAgainName", "parola din nou"},
            {"welcomeMsg", "Alăturați-vă"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}