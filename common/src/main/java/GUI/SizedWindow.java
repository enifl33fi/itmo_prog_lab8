package GUI;

import java.awt.*;

public interface SizedWindow {
    default void setToCenter(Window window){
        int width = window.getWidth();
        int height = window.getHeight();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        window.setBounds((dimension.width - width) / 2, (dimension.height - height) / 2, width, height);
    }
}
