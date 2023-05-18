package GUI.VisualizationPage;

import GUI.dialogs.VisualizationDialog;
import custom.*;
import element.CollectionPart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class VisualizationPageModel extends JFrame{
    private JPanel visualizationPagePanel;
    private JButton tableButton;
    private CustomUserZone customUserZone;
    private CommandPanel commandPanel;
    private VisualPanel visualPanel;
    private final VisualizationPageLogic logic;
    private String user;

    public VisualizationPageModel(VisualizationPageLogic visualizationPageLogic){
        logic = visualizationPageLogic;
        customUserZone.setBackground(visualizationPagePanel.getBackground());
        customUserZone.setComponentBackground(visualizationPagePanel.getBackground());
        setSize(800, 600);
        logic.setSettings(this, visualizationPagePanel, "visualization");
        commandPanel.setBackground(visualizationPagePanel.getBackground());
        for (CommandButton commandButton: commandPanel.getContainedButtons()){
            commandButton.addActionListener((e) -> logic.execute(commandButton));
        }
        tableButton.setBackground(Color.LIGHT_GRAY);
        tableButton.setForeground(Color.WHITE);
        customUserZone.getHelpButton().addActionListener((e) -> logic.help());
        customUserZone.getLogOutButton().addActionListener((e) -> logic.goBack());
        tableButton.addActionListener((e) -> logic.goFurther());
        visualPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PaintComponent component = visualPanel.getSelected(e.getX(), e.getY());
                if (component != null){
                    new VisualizationDialog(component, user.equals(component.getElem().getOwner()), logic);
                }
            }
        });
    }
    protected void setUserName(String login){
        customUserZone.setUserName(login);
        user = login;
    }

    private void createUIComponents() {
        commandPanel = new CommandPanel();
        customUserZone = new CustomUserZone();
        visualPanel = new VisualPanel();
    }
    protected void setData(LinkedList<CollectionPart> data){
        visualPanel.updateVisualization(data);
    }
    public void switchLanguage(){
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.VisualizationPage");
        fillLabels(r);

    }
    private void fillLabels(ResourceBundle r){
        tableButton.setText(r.getString("tableButton"));
    }
}
