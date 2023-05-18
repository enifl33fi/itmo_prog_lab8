package GUI.bundles;

import java.util.ListResourceBundle;

public class RegOrEnter_es_SV extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "volver"},
            {"signUpButton", "inscripción"},
            {"signInButton", "conectarse"},
            {"field", "Escriba a "},
            {"loginName", "inicio de sesión"},
            {"passwordName", "contraseña"},
            {"passwordAgainName", "contraseña de nuevo"},
            {"welcomeMsg", "Únete a"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
