package GUI.bundles;

import java.util.ListResourceBundle;

public class ElementDialog_es_SV extends ListResourceBundle {
    private Object[][] contents = {{"nameField", "nombre"},
            {"xCoordinateField", "x coordenadas"},
            {"xCoordinateField", "y coordenadas"},
            {"healthField", "salud"},
            {"heartCountField", "número de corazones"},
            {"chapterNameField", "título del capítulo"},
            {"marinesCountField", "número de paracaidistas"},
            {"creationDateField", "fecha de creación"},
            {"idField", "id"},
            {"field", "Escriba a "},
            {"okButton", "ok"},
            {"cancelButton", "cancelación"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
