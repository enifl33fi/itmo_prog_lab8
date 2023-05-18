package GUI.bundles;

import java.util.ListResourceBundle;

public class RegOrEnter_en extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "back"},
            {"signUpButton", "sign up"},
            {"signInButton", "sign in"},
            {"field", "Type "},
            {"loginName", "login"},
            {"passwordName", "password"},
            {"passwordAgainName", "password again"},
            {"welcomeMsg", "Join us"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
