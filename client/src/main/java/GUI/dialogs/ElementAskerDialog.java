package GUI.dialogs;

import GUI.SizedWindow;
import custom.CustomField;
import custom.CustomTextField;
import element.AstartesCategory;
import element.MeleeWeapon;
import exceptions.NullFieldException;
import exceptions.WrongFieldException;
import general.GeneralVars;
import general.validator.GeneralValidator;
import network.requests.Request;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ElementAskerDialog extends JDialog implements SizedWindow {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private CustomTextField nameField;
    private CustomTextField xCoordinateField;
    private CustomTextField yCoordinateField;
    private CustomTextField healthField;
    private CustomTextField marinesCountField;
    private CustomTextField heartCountField;
    private JComboBox<Object> categoryComboBox;
    private JComboBox<Object> weaponComboBox;
    private CustomTextField chapterNameField;

    private final GeneralValidator validator = new GeneralValidator();
    private final ArrayList<CustomTextField> checkFields = new ArrayList<>();
    private final Request req;
    private final String[] givenValues = new String[GeneralVars.USERS_VAR_COUNT];

    public ElementAskerDialog(Request request) {
        req = request;
        givenValues[5] = categoryComboBox.getSelectedItem().toString();
        givenValues[6] = weaponComboBox.getSelectedItem().toString();
        setContentPane(contentPane);
        setModal(true);
        checkFields.add(nameField);
        checkFields.add(xCoordinateField);
        checkFields.add(yCoordinateField);
        checkFields.add(healthField);
        checkFields.add(heartCountField);
        checkFields.add(chapterNameField);
        checkFields.add(marinesCountField);
        for (CustomField customField:checkFields){
            customField.setBackground(contentPane.getBackground());
        }

        categoryComboBox.setBackground(contentPane.getBackground());
        weaponComboBox.setBackground(contentPane.getBackground());
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        nameField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkName(nameField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkName(nameField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkName(nameField);
            }
        });
        xCoordinateField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkX(xCoordinateField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkX(xCoordinateField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkX(xCoordinateField);
            }
        });
        yCoordinateField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkY(yCoordinateField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkY(yCoordinateField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkY(yCoordinateField);
            }
        });
        healthField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkHealth(healthField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkHealth(healthField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkHealth(healthField);
            }
        });
        heartCountField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkHeartCount(heartCountField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkHeartCount(heartCountField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkHeartCount(heartCountField);
            }
        });
        chapterNameField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkChapterName(chapterNameField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkChapterName(chapterNameField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkChapterName(chapterNameField);
            }
        });
        marinesCountField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkMarinesCount(marinesCountField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkMarinesCount(marinesCountField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkMarinesCount(marinesCountField);
            }
        });
        categoryComboBox.addActionListener((e) -> getComboBoxValue(categoryComboBox, 5));
        weaponComboBox.addActionListener((e) -> getComboBoxValue(weaponComboBox, 6));
        pack();
        setToCenter(this);
        setVisible(true);
    }

    private void onOK() {
        if (check()){
            req.setElement(validator.validateSpaceMarine(givenValues, req.getLogin()));
            dispose();
        }
    }

    private void onCancel() {
        req.setElement(null);
        dispose();
    }
    private void createUIComponents() {
        nameField = new CustomTextField("name");
        xCoordinateField = new CustomTextField("x coordinate");
        yCoordinateField = new CustomTextField("y coordinate");
        healthField = new CustomTextField("health");
        heartCountField = new CustomTextField("heart count");
        chapterNameField = new CustomTextField("chapter name");
        marinesCountField = new CustomTextField("marines count");
        healthField.setHint(false);
        categoryComboBox = new JComboBox<>(Arrays.stream(AstartesCategory.values()).map(AstartesCategory::toString).toArray());
        categoryComboBox.addItem("");
        weaponComboBox = new JComboBox<>(Arrays.stream(MeleeWeapon.values()).map(MeleeWeapon::toString).toArray());
        weaponComboBox.addItem("");
    }


    private void checkName(CustomTextField field){
        try {
            validator.validateName(field.getInputText());
            givenValues[0] = field.getInputText();
            field.setWarn(" ");
        } catch (NullFieldException e){
            field.setWarn(e.getMessage());
        }
    }
    private void checkX(CustomTextField field){
        try {
            validator.validateX(field.getInputText());
            givenValues[1] = field.getInputText();
            field.setWarn(" ");
        } catch (NullFieldException | WrongFieldException | NumberFormatException e){
            field.setWarn(e.getMessage());
        }
    }
    private void checkY(CustomTextField field){
        try {
            validator.validateY(field.getInputText());
            givenValues[2] = field.getInputText();
            yCoordinateField.setWarn(" ");
        } catch (NullFieldException | WrongFieldException | NumberFormatException e){
            yCoordinateField.setWarn(e.getMessage());
        }
    }
    private void checkHealth(CustomTextField field){
        try {
            validator.validateHealth(field.getInputText());
            givenValues[3] = field.getInputText();
            healthField.setWarn(" ");
        } catch (WrongFieldException | NumberFormatException e){
            healthField.setWarn(e.getMessage());
        }
    }
    private void checkHeartCount(CustomTextField field){
        try {
            validator.validateHeartCount(field.getInputText());
            givenValues[4] = field.getInputText();
            heartCountField.setWarn(" ");
        } catch (NullFieldException | WrongFieldException | NumberFormatException e){
            heartCountField.setWarn(e.getMessage());
        }
    }
    private void getComboBoxValue(JComboBox<Object> comboBox, int pos){
        givenValues[pos] = comboBox.getSelectedItem().toString();
    }
    private void checkChapterName(CustomTextField field){
        try {
            validator.validateChapterName(field.getInputText());
            givenValues[7] = field.getInputText();
            chapterNameField.setWarn(" ");
        } catch (NullFieldException e){
            chapterNameField.setWarn(e.getMessage());
        }
    }
    private void checkMarinesCount(CustomTextField field){
        try {
            validator.validateMarinesCount(field.getInputText());
            givenValues[8] = field.getInputText();
            marinesCountField.setWarn(" ");
        } catch (NullFieldException | WrongFieldException | NumberFormatException e){
            marinesCountField.setWarn(e.getMessage());
        }
    }
    private boolean checkAccess(CustomField field){
        return field.getWarnMsg().getText().isBlank() && field.getInputField().getForeground() != Color.GRAY;
    }
    private boolean check(){
        for (CustomField customField : checkFields){
            if (!checkAccess(customField)){
                return false;
            }
        }
        return true;
    }
}
