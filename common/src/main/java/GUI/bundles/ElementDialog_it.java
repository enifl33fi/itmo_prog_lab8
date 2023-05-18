package GUI.bundles;

import java.util.ListResourceBundle;

public class ElementDialog_it extends ListResourceBundle {
    private Object[][] contents = {{"nameField", "nome"},
            {"xCoordinateField", "x coordinare"},
            {"yCoordinateField", "y coordinare"},
            {"healthField", "salute"},
            {"heartCountField", "numero di cuori"},
            {"chapterNameField", "titolo del capitolo"},
            {"marinesCountField", "количество десантников"},
            {"creationDateField", "numero di paracadutisti"},
            {"idField", "id"},
            {"field", "Scrivere "},
            {"okButton", "ok"},
            {"cancelButton", "annullamento"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
