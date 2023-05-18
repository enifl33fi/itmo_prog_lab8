package GUI.dialogs;

import GUI.SizedWindow;
import GUI.VisualizationPage.VisualizationPageLogic;
import custom.PaintComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VisualizationDialog extends JDialog implements SizedWindow {
    private JPanel contentPane;
    private JButton buttonUpdate;
    private JButton buttonRemove;
    private JTextArea showElementArea;
    private final VisualizationPageLogic logic;
    private final PaintComponent component;

    public VisualizationDialog(PaintComponent component, boolean isOwner, VisualizationPageLogic visualizationPageLogic) {
        this.component = component;
        logic = visualizationPageLogic;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonUpdate);
        showElementArea.setText(component.getElem().toString());
        showElementArea.setEditable(false);
        showElementArea.setBackground(contentPane.getBackground());
        showElementArea.setPreferredSize(new Dimension(300, 225));
        buttonUpdate.setVisible(isOwner);
        buttonRemove.setVisible(isOwner);
        buttonUpdate.addActionListener(e -> onUpdate());
        buttonRemove.addActionListener(e -> onRemove());

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

    private void onUpdate() {
        logic.update(component);
        dispose();
    }
    private void onRemove() {
        logic.remove(component.getElem().getId());
        dispose();
    }

    private void onCancel() {
        dispose();
    }

}
