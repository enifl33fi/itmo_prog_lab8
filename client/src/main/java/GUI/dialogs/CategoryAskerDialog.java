package GUI.dialogs;

import GUI.SizedWindow;
import element.AstartesCategory;
import network.requests.Request;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class CategoryAskerDialog extends JDialog implements SizedWindow {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<Object> categoryComboBox;
    private final Request req;

    public CategoryAskerDialog(Request request) {
        req = request;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        categoryComboBox.setBackground(contentPane.getBackground());

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
        String category = categoryComboBox.getSelectedItem().toString();
        dispose();
        req.setArg(category);
    }

    private void onCancel() {
        dispose();
        req.setArg(null);
    }


    private void createUIComponents() {
        categoryComboBox = new JComboBox<>(Arrays.stream(AstartesCategory.values()).map(AstartesCategory::toString).toArray());
        categoryComboBox.addItem("");
    }
}
