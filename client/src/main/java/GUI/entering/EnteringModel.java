package GUI.entering;

import custom.CustomField;
import custom.CustomPasswordField;
import custom.CustomTextField;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EnteringModel extends JFrame{
    private JPanel enteringPanel;
    private CustomTextField loginField;
    private CustomPasswordField firstPasswordField;
    private JButton signInButton;
    private JButton backButton;
    private final EnteringLogic logic;
    private final ArrayList<CustomField> checkFields = new ArrayList<>();

    public EnteringModel(EnteringLogic enteringLogic){
        this.logic = enteringLogic;
        setSize(500, 400);
        logic.setSettings(this, enteringPanel, "enter");
        loginField.setBackground(enteringPanel.getBackground());
        firstPasswordField.setBackground(enteringPanel.getBackground());
        checkFields.add(loginField);
        checkFields.add(firstPasswordField);
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
        backButton.addActionListener((e) -> logic.goBack());
        signInButton.addActionListener((e) -> logic.enter());
    }

    private void createUIComponents() {
        loginField = new CustomTextField("login");
        firstPasswordField = new CustomPasswordField("password");
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
        return enteringPanel;
    }
    public void switchLanguage(){
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.RegOrEnter");
        fillLabels(r);

    }
    private void fillLabels(ResourceBundle r){
        signInButton.setText(r.getString("signInButton"));
        backButton.setText(r.getString("backButton"));
        loginField.setHelpMsg(r.getString("field") + r.getString("loginName"));
        firstPasswordField.setHelpMsg(r.getString("field") + r.getString("passwordName"));
    }
}
