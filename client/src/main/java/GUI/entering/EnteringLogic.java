package GUI.entering;

import GUI.WorkingWindow;
import GUI.WorkingWindowAdapter;
import client.Client;
import custom.CustomPasswordField;
import custom.CustomTextField;
import exceptions.NullFieldException;
import exceptions.WrongFieldException;
import general.validator.GeneralValidator;

import javax.swing.*;

public class EnteringLogic extends WorkingWindowAdapter {
    private final EnteringModel model = new EnteringModel(this);
    private final GeneralValidator validator = new GeneralValidator();
    private final Client client;

    public EnteringLogic(Client client){
        this.client = client;
    }

    public void checkLogin(CustomTextField loginField) {
        try {
            String login = validator.validateLogin(loginField.getInputText());
            loginField.setWarn(" ");
        }  catch (NullFieldException | WrongFieldException e){
            loginField.getWarnMsg().setText(e.getMessage());
        }
    }

    public void checkPswd(CustomPasswordField passwordField){
        try {
            validator.validatePassword(passwordField.getInputField().getPassword());
            passwordField.getWarnMsg().setText(" ");

        } catch (NullFieldException e){
            passwordField.getWarnMsg().setText(e.getMessage());
        }
    }
    @Override
    public void goBack(){
        model.dispose();
        client.loadStartPage();
    }
    public void enter(){
        if (model.check()){
            client.register(model.getUser(), false);
        }
    }
    @Override
    public void setIt(){
        model.setVisible(true);
    }
    @Override
    public void goFurther(){
        model.dispose();
        client.loadTablePage();
    }
}
