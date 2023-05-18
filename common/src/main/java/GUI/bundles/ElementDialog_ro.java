package GUI.bundles;

import java.util.ListResourceBundle;

public class ElementDialog_ro extends ListResourceBundle {
    private Object[][] contents = {{"nameField", "name"},
            {"xCoordinateField", "x coordonata"},
            {"yCoordinateField", "y coordonata"},
            {"healthField", "health"},
            {"heartCountField", "număr de inimi"},
            {"chapterNameField", "chapter name"},
            {"marinesCountField", "număr de pușcași marini"},
            {"creationDateField", "data creării"},
            {"idField", "id"},
            {"field", "Scrie "},
            {"okButton", "ok"},
            {"cancelButton", "anulare"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
