package GUI.dialogs;

import GUI.SizedWindow;
import GUI.WorkingWindow;
import custom.CustomTextField;
import network.requests.Request;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class IdAskerDialog extends JDialog implements SizedWindow {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private CustomTextField idInputField;
    private final Request req;

    public IdAskerDialog(Request request, WorkingWindow workingWindow) {
        this.req = request;
        setContentPane(contentPane);
        setModal(true);
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
        idInputField.getInputField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkId(workingWindow);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkId(workingWindow);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkId(workingWindow);
            }
        });
        idInputField.setBackground(contentPane.getBackground());
        pack();
        setToCenter(this);
        setVisible(true);
    }

    private void onOK() {
        if (idInputField.getWarnMsg().getText().isBlank() && idInputField.getInputField().getForeground() != Color.GRAY){
            req.setArg(idInputField.getInputField().getText());
            dispose();
        }
    }

    private void onCancel() {
        req.setArg(null);
        dispose();
    }
    private void createUIComponents() {
        idInputField = new CustomTextField("id");
    }
    private void checkId(WorkingWindow workingWindow){
        String result = workingWindow.checkId(idInputField.getInputField().getText());
        idInputField.getWarnMsg().setText(result);
    }


}
