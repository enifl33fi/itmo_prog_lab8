package custom;

import commands.CommandType;

import javax.swing.*;
import java.util.ArrayList;

public class CommandPanel extends JPanel {
    private final ArrayList<CommandButton> containedButtons = new ArrayList<>();
    public CommandPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        fill();
    }

    private void fill(){
        for(CommandType commandType:CommandType.values()){
            if(commandType.isDisplaying()){
                CommandButton newCommand = new CommandButton(commandType.toString());
                containedButtons.add(newCommand);
                this.add(newCommand);
                this.add(Box.createVerticalStrut(14));
            }
        }
    }

    public ArrayList<CommandButton> getContainedButtons() {
        return containedButtons;
    }
}
