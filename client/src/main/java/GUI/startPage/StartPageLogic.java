package GUI.startPage;

import GUI.WorkingWindow;
import GUI.WorkingWindowAdapter;
import client.Client;

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
        model.setVisible(true);
    }

}
