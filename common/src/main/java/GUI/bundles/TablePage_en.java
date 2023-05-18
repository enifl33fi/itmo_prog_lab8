package GUI.bundles;

import java.util.ListResourceBundle;

public class TablePage_en extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "back"},
            {"visualizationButton", "visualization"},
            {"okButton", "ok"},
            {"cancelButton", "cancel"},
            {"filterButton", "filter"},
            {"removeButton", "remove"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
