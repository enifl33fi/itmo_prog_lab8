package GUI;

import element.CollectionPart;
import network.requests.Request;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public interface WorkingWindow extends SizedWindow{
    default void setSettings(JFrame jFrame, JPanel panel, String title){
        jFrame.setTitle(title);
        setToCenter(jFrame);
        jFrame.setContentPane(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    void raiseFileDialog(Request req);
    void raiseDialog(String message, int messageType);
    void setIt();
    void goFurther();
    void goBack();
    void raiseCustomDialog(ArgumentType type, Request req);
    String checkId(String idStr);

    void setData(LinkedList<CollectionPart> data);
}
