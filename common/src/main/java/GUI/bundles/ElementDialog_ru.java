package GUI.bundles;

import java.util.ListResourceBundle;

public class ElementDialog_ru extends ListResourceBundle {
    private Object[][] contents = {{"nameField", "имя"},
            {"xCoordinateField", "x координата"},
            {"yCoordinateField", "y координата"},
            {"healthField", "здоровье"},
            {"heartCountField", "количество сердец"},
            {"chapterNameField", "название главы"},
            {"marinesCountField", "количество десантников"},
            {"creationDateField", "дата создания"},
            {"idField", "ид"},
            {"field", "Напишите "},
            {"okButton", "ок"},
            {"cancelButton", "отмена"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
