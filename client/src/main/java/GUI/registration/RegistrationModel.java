package GUI.registration;

import custom.CustomField;
import custom.CustomPasswordField;
import custom.CustomTextField;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class RegistrationModel extends JFrame {
    private JPanel registrationPanel;
    private CustomTextField loginField;
    private CustomPasswordField firstPasswordField;
    private CustomPasswordField secondPasswordField;
    private JButton signUpButton;
    private JButton backButton;
    private final RegistrationLogic logic;
    private final ArrayList<CustomField> checkFields = new ArrayList<>();

    public RegistrationModel(RegistrationLogic registrationLogic){
        setSize(500, 300);
        this.logic = registrationLogic;
        logic.setSettings(this, registrationPanel, "registration");
        loginField.setBackground(registrationPanel.getBackground());
        firstPasswordField.setBackground(registrationPanel.getBackground());
        secondPasswordField.setBackground(registrationPanel.getBackground());
        checkFields.add(loginField);
        checkFields.add(firstPasswordField);
        checkFields.add(secondPasswordField);
        loginField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                logic.checkLogin(loginField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                logic.checkLogin(loginField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                logic.checkLogin(loginField);
            }
        });
        firstPasswordField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                logic.checkPswd(firstPasswordField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                logic.checkPswd(firstPasswordField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                logic.checkPswd(firstPasswordField);
            }
        });
        secondPasswordField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                logic.checkSecondPswd(secondPasswordField, firstPasswordField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                logic.checkSecondPswd(secondPasswordField, firstPasswordField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                logic.checkSecondPswd(secondPasswordField, firstPasswordField);
            }
        });
        backButton.addActionListener((e) -> logic.goBack());
        signUpButton.addActionListener((e) -> logic.register());

    }

    private void createUIComponents() {
        loginField = new CustomTextField("login");
        firstPasswordField = new CustomPasswordField("password");
        secondPasswordField = new CustomPasswordField("password again");
    }
    private boolean checkAccess(CustomField customTextField){
        return customTextField.getWarnMsg().getText().isBlank() && customTextField.getInputField().getForeground() != Color.GRAY;
    }
    protected boolean check(){
        for (CustomField customField : checkFields){
            if (!checkAccess(customField)){
                return false;
            }
        }
        return true;
    }
    protected ImmutablePair<String, String> getUser(){
        return new ImmutablePair<>(loginField.getInputField().getText(), String.valueOf(firstPasswordField.getInputField().getPassword()));
    }
    protected JPanel getPanel(){
        return registrationPanel;
    }

}
