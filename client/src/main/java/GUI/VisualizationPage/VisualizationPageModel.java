package GUI.VisualizationPage;

import custom.CommandButton;
import custom.CommandPanel;
import custom.CustomUserZone;
import custom.VisualPanel;
import element.CollectionPart;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class VisualizationPageModel extends JFrame{
    private JPanel visualizationPagePanel;
    private JButton tableButton;
    private CustomUserZone customUserZone;
    private CommandPanel commandPanel;
    private VisualPanel visualPanel;
    private final VisualizationPageLogic logic;

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
    }
    protected void setUserName(String login){
        customUserZone.setUserName(login);
    }

    private void createUIComponents() {
        commandPanel = new CommandPanel();
        customUserZone = new CustomUserZone();
        visualPanel = new VisualPanel();
    }
    protected void setData(LinkedList<CollectionPart> data){
        visualPanel.updateVisualization(data);
    }
}
