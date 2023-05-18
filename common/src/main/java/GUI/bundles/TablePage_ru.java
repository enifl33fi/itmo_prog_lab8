package GUI.bundles;

import java.util.ListResourceBundle;

public class TablePage_ru extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "назад"},
            {"visualizationButton", "визуализация"},
            {"okButton", "ок"},
            {"cancelButton", "отмена"},
            {"filterButton", "фильтр"},
            {"removeButton", "удалить"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
