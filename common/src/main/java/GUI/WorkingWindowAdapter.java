package GUI;

import element.CollectionPart;
import network.requests.Request;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public abstract class WorkingWindowAdapter implements WorkingWindow {
    @Override
    public void raiseDialog(String message, int messageType){
        JScrollPane scroll = new JScrollPane(new JTextArea(message)){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 225);
            }
        };
        JOptionPane.showMessageDialog(null, scroll, "message", messageType);

    }
    @Override
    public void raiseFileDialog(Request req) {
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int state = fileChooser.showOpenDialog(null);
        if (state == 0){
            req.setArg(fileChooser.getSelectedFile().getAbsolutePath());
        } else {
            req.setArg(null);
        }
    }
    @Override
    public void setIt(){

    }
    @Override
    public void goFurther(){

    }
    @Override
    public void goBack(){

    }
    @Override
    public void raiseCustomDialog(ArgumentType type, Request req){

    }
    @Override
    public String checkId(String idStr){
        return " ";
    }
    @Override
    public void setData(LinkedList<CollectionPart> data){

    }
}
