package custom;

import commands.CommandType;

import javax.swing.*;
import java.awt.*;

public class CommandButton extends JButton implements RequestButton{
    public CommandButton(String name, Dimension size){
        setMinimumSize(size);
        setMaximumSize(size);
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.WHITE);
        setText(name);
    }
    public CommandButton(String name){
        this(name, new Dimension(100, 25));
    }
    public CommandType getCommandType(){
        if (getText().isEmpty()){
            return CommandType.HELP;
        }
        return CommandType.getByName(getText());
    }
}
