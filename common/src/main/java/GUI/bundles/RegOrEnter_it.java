package GUI.bundles;

import java.util.ListResourceBundle;

public class RegOrEnter_it extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "indietro"},
            {"signUpButton", "registrazione"},
            {"signInButton", "accedere"},
            {"field", "Scrivere "},
            {"loginName", "accesso"},
            {"passwordName", "password"},
            {"passwordAgainName", "password di nuovo"},
            {"welcomeMsg", "Unirsi"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
