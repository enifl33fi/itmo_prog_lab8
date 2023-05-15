package GUI.startPage;

import client.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class StartPageModel extends JFrame{
    private JPanel startPagePanel;
    private JLabel startPicLabel;
    private JLabel startTitleLabel;
    private JButton signUpButton;
    private JButton signInButton;
    private ImageIcon startIcon;
    private final StartPageLogic logic;

    public StartPageModel(StartPageLogic startPageLogic){
        this.logic = startPageLogic;
        setSize(500, 300);
        logic.setSettings(this, startPagePanel, "start page");
        try{
            startIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("img/startPage/startPic.png"))));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        startPicLabel.setIcon(startIcon);
        startTitleLabel.setForeground(Color.GRAY);
        startTitleLabel.setFont(startTitleLabel.getFont().deriveFont(20f));
        signUpButton.addActionListener((e) -> logic.goToRegistration());
        signInButton.addActionListener((e) -> logic.goToEntering());

    }
}
