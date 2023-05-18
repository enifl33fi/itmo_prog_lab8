package GUI.bundles;

import java.util.ListResourceBundle;

public class ElementDialog_en extends ListResourceBundle {
    private Object[][] contents = {{"nameField", "name"},
            {"xCoordinateField", "x coordinate"},
            {"yCoordinateField", "y coordinate"},
            {"healthField", "health"},
            {"heartCountField", "heart count"},
            {"chapterNameField", "chapter name"},
            {"marinesCountField", "marines count"},
            {"creationDateField", "creation date"},
            {"idField", "id"},
            {"field", "Type "},
            {"okButton", "ok"},
            {"cancelButton", "cancel"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}