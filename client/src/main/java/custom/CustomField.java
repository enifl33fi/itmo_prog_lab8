package custom;

import javax.swing.*;
import java.awt.*;

public interface CustomField {
    JComponent getInputField();
    JLabel getWarnMsg();
    void setBackground(Color color);
}
