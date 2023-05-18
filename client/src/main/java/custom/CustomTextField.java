package custom;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CustomTextField extends JPanel implements CustomField{
    private final String inputFieldName;
    private final JLabel helpMsg = new JLabel("Type ");
    private final JTextField inputField = new JTextField();
    private final JLabel warnMsg = new JLabel(" ");
    private final Dimension prefDimension = new Dimension(150, 20);
    private boolean hintIsActivated = true;

    public CustomTextField(String name){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        inputField.setForeground(Color.GRAY);
        warnMsg.setForeground(Color.RED);
        inputFieldName = name;
        inputField.setText(inputFieldName);
        helpMsg.setForeground(Color.GRAY);
        helpMsg.setText(helpMsg.getText() + inputFieldName);
        helpMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        warnMsg.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(helpMsg);
        this.add(inputField);
        this.add(warnMsg);
        inputField.setMinimumSize(prefDimension);
        inputField.setMaximumSize(prefDimension);
        inputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getForeground() == Color.GRAY && hintIsActivated){
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputField.getText().equals("") && hintIsActivated){
                    inputField.setForeground(Color.GRAY);
                    inputField.setText(inputFieldName);
                }
            }
        });
    }
    public JTextField getInputField(){
        return inputField;
    }
    public JLabel getWarnMsg(){
        return warnMsg;
    }
    public String getInputText(){
        return inputField.getText();
    }
    public void setWarn(String warnStr){
        warnMsg.setText(warnStr);
    }
    public void setHint(boolean isActivated){
        hintIsActivated = isActivated;
        if (isActivated){
            hintWhenFocusLost();
        } else {
            hintWhenFocusGained();
        }
    }
    private void hintWhenFocusGained(){
        inputField.setText("");
        inputField.setForeground(Color.BLACK);
    }
    private void hintWhenFocusLost(){
        inputField.setForeground(Color.GRAY);
        inputField.setText(inputFieldName);
    }
    public void setHelpMsg(String helpString){
        helpMsg.setText(helpString);
    }
}
