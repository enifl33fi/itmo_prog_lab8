package custom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class CustomUserZone extends JPanel {
    private final JLabel userPic;
    private final JLabel userName;
    private final JButton logOutButton;
    private final JButton helpButton;
    private ImageIcon userIcon;
    private ImageIcon logOutIcon;
    private ImageIcon helpIcon;
    public CustomUserZone(){
        userName = new JLabel(" ");
        userPic = new JLabel();
        logOutButton = new JButton();
        userName.setForeground(Color.GRAY);
        logOutButton.setBorder(null);
        try{
            helpIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("img/mainPages/help.png"))));
            userIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("img/mainPages/profile.png"))));
            logOutIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("img/mainPages/logOut.png"))));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        helpButton = new CommandButton("", new Dimension(helpIcon.getIconWidth(), helpIcon.getIconHeight()));
        helpButton.setBorder(null);
        helpButton.setIcon(helpIcon);
        userPic.setIcon(userIcon);
        logOutButton.setIcon(logOutIcon);
        add(helpButton);
        add(Box.createHorizontalStrut(10));
        add(userName);
        add(userPic);
        add(Box.createHorizontalGlue());
        add(logOutButton);
    }
    public void setComponentBackground(Color color){
        logOutButton.setBackground(color);
        helpButton.setBackground(color);

    }
    public void setUserName(String login){
        userName.setText(login);
    }
    public JButton getHelpButton(){
        return helpButton;
    }
    public JButton getLogOutButton(){
        return logOutButton;
    }
}
