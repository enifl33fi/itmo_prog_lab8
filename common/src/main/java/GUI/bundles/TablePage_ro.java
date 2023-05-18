package GUI.bundles;

import java.util.ListResourceBundle;

public class TablePage_ro extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "spate"},
            {"visualizationButton", "vizualizare"},
            {"okButton", "ok"},
            {"cancelButton", "anulare"},
            {"filterButton", "filtru"},
            {"removeButton", "șterge"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}