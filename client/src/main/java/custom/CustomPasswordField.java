package custom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.Objects;

public class CustomPasswordField extends JPanel implements CustomField{
    private final JPanel pswdPanel = new JPanel(new BorderLayout());
    private ImageIcon activeButtonIcon;
    private ImageIcon shoutDownButtonIcon;
    private boolean buttonIsActive = false;
    private final String inputFieldName;
    private final JPasswordField inputField = new JPasswordField();
    private final JButton inputShowButton = new JButton();
    private final JLabel helpMsg = new JLabel("Type ");
    private final JLabel warnMsg = new JLabel(" ");
    private final Dimension inputFieldDimension = new Dimension(150, 20);
    public CustomPasswordField(String name){
        //tip string
        inputField.setEchoChar((char) 0);
        inputField.setForeground(Color.GRAY);
        inputFieldName = name;
        inputField.setText(inputFieldName);
        //img load
        try{
            activeButtonIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("img/closeEye.png"))).getScaledInstance(14,14, Image.SCALE_SMOOTH));
            shoutDownButtonIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("img/openEye.png"))).getScaledInstance(14,14, Image.SCALE_SMOOTH));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        //show password field
        pswdPanel.add(inputField, BorderLayout.CENTER);
        //tip disappearing
        inputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getForeground() == Color.GRAY){
                    setMode();
                    inputField.setForeground(Color.BLACK);
                    inputField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputField.getPassword().length == 0){
                    inputField.setEchoChar((char) 0);
                    inputField.setForeground(Color.GRAY);
                    inputField.setText(inputFieldName);
                }
            }
        });
        // min size of password field
        inputField.setMinimumSize(inputFieldDimension);
        // creating and adding button
        inputShowButton.setIcon(shoutDownButtonIcon);
        inputShowButton.setContentAreaFilled(false);
        inputShowButton.setMargin(new Insets(0,0,0,0));
        inputShowButton.setBorder(null);
        pswdPanel.add(inputShowButton, BorderLayout.EAST);
        inputShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonIsActive = !buttonIsActive;
                setIcon();
                if (inputField.getForeground() != Color.GRAY){
                    setMode();
                }
            }
        });
        pswdPanel.setBackground(inputField.getBackground());
        pswdPanel.setBorder(inputField.getBorder());
        inputField.setBorder(null);
        pswdPanel.setMaximumSize(inputFieldDimension);
        helpMsg.setForeground(Color.GRAY);
        helpMsg.setText(helpMsg.getText() + inputFieldName);
        helpMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        warnMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        // adding warn message
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(helpMsg);
        this.add(pswdPanel);
        warnMsg.setForeground(Color.RED);
        this.add(warnMsg);

    }

    public JPasswordField getInputField(){
        return inputField;
    }
    public JLabel getWarnMsg(){
        return warnMsg;
    }
    public void setIcon(){
        if(buttonIsActive){
            inputShowButton.setIcon(activeButtonIcon);
        } else {
            inputShowButton.setIcon(shoutDownButtonIcon);
        }
    }
    public void setMode(){
        if(buttonIsActive){
            inputField.setEchoChar((char) 0);
        } else {
            inputField.setEchoChar('*');
        }
    }
    public void setHelpMsg(String helpString){
        helpMsg.setText(helpString);
    }
}
