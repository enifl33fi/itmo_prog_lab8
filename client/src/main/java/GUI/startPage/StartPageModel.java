package GUI.startPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartPageModel extends JFrame{
    private JPanel startPagePanel;
    private JLabel startPicLabel;
    private JLabel startTitleLabel;
    private JButton signUpButton;
    private JButton signInButton;
    private JComboBox langChangeComboBox;
    private ImageIcon startIcon;
    private final StartPageLogic logic;

    public StartPageModel(StartPageLogic startPageLogic){
        this.logic = startPageLogic;
        //Locale.setDefault(Locale.ENGLISH);
        setSize(500, 400);
        logic.setSettings(this, startPagePanel, "start page");
        try{
            startIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("img/startPage/startPic.png"))));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        startPicLabel.setIcon(startIcon);
        langChangeComboBox.setBackground(startPagePanel.getBackground());
        langChangeComboBox.addActionListener((e -> {
            switch (langChangeComboBox.getSelectedItem().toString()){
                case "en" -> Locale.setDefault(Locale.ENGLISH);
                case "ru" -> Locale.setDefault(new Locale.Builder().setLanguage("ru").build());
                case "ro" -> Locale.setDefault(new Locale.Builder().setLanguage("ro").build());
                case "it" -> Locale.setDefault(Locale.ITALIAN);
                case "es_SV" -> Locale.setDefault(new Locale.Builder().setLanguage("es").setRegion("SV").build());
            }
            switchLanguage();
        }));
        startTitleLabel.setForeground(Color.GRAY);
        startTitleLabel.setFont(startTitleLabel.getFont().deriveFont(20f));
        signUpButton.addActionListener((e) -> logic.goToRegistration());
        signInButton.addActionListener((e) -> logic.goToEntering());

    }
    public void switchLanguage(){
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.RegOrEnter");
        fillLabels(r);

    }
    private void fillLabels(ResourceBundle r){
        startTitleLabel.setText(r.getString("welcomeMsg"));
        signUpButton.setText(r.getString("signUpButton"));
        signInButton.setText(r.getString("signInButton"));

    }

    private void createUIComponents() {
        langChangeComboBox = new JComboBox<>(new String[]{"en", "ru", "ro", "it", "es_SV"});

    }
}
