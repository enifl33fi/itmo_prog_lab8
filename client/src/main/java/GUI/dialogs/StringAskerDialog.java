package GUI.dialogs;

import GUI.SizedWindow;
import custom.CustomTextField;
import network.requests.Request;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StringAskerDialog extends JDialog implements SizedWindow {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private CustomTextField namePartField;
    private final Request req;

    public StringAskerDialog(Request request) {
        req = request;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        namePartField.setBackground(contentPane.getBackground());

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
        pack();
        setToCenter(this);
        setVisible(true);
    }

    private void onOK() {
        String namePart = "";
        if (namePartField.getInputField().getForeground() != Color.GRAY){
            namePart = namePartField.getInputField().getText();
        }
        req.setArg(namePart);
        dispose();
    }

    private void onCancel() {
        dispose();
        req.setArg(null);
    }
    private void createUIComponents() {
        namePartField = new CustomTextField("name part");
    }

}
