package GUI.registration;

import GUI.WorkingWindowAdapter;
import client.Client;
import custom.CustomPasswordField;
import custom.CustomTextField;
import exceptions.NullFieldException;
import exceptions.WrongFieldException;
import general.validator.GeneralValidator;


public class RegistrationLogic extends WorkingWindowAdapter {
    public final GeneralValidator validator = new GeneralValidator();
    public final RegistrationModel model = new RegistrationModel(this);
    public final Client client;

    public RegistrationLogic(Client client){
        this.client = client;
    }
    @Override
    public void setIt(){
        model.switchLanguage();
        model.setVisible(true);
    }
    public void checkLogin(CustomTextField loginField){
        try {
            String login = validator.validateLogin(loginField.getInputText());
            loginField.setWarn(client.checkLoginUnique(loginField.getInputText()));
        } catch (NullFieldException | WrongFieldException e){
            loginField.setWarn(e.getMessage());
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
    public void checkSecondPswd(CustomPasswordField secondPasswordField, CustomPasswordField firstPasswordField){
        try {
            validator.validateSecondPassword(secondPasswordField.getInputField().getPassword(), firstPasswordField.getInputField().getPassword());
            secondPasswordField.getWarnMsg().setText(" ");
        } catch (WrongFieldException e){
            secondPasswordField.getWarnMsg().setText(e.getMessage());
        }
    }
    @Override
    public void goBack(){
        model.dispose();
        client.loadStartPage();
    }
    public void register(){
        if (model.check()){
            client.register(model.getUser(), true);
        }
    }
    @Override
    public void goFurther(){
        model.dispose();
        client.loadTablePage();
    }
}
