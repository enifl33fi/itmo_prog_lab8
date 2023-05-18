package GUI.startPage;

import GUI.WorkingWindowAdapter;
import client.Client;

import java.util.Locale;
import java.util.ResourceBundle;

public class StartPageLogic extends WorkingWindowAdapter {
    private final StartPageModel model = new StartPageModel(this);
    private final Client client;

    public StartPageLogic(Client client) {
        this.client = client;
    }

    public void goToRegistration(){
        model.dispose();
        client.loadRegistration();
    }
    public void goToEntering(){
        model.dispose();
        client.loadEntering();
    }
    @Override
    public void setIt(){
        model.switchLanguage();
        model.setVisible(true);
    }
}
