package GUI.bundles;

import java.util.ListResourceBundle;

public class TablePage_it extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "indietro"},
            {"visualizationButton", "visualizzazione"},
            {"okButton", "ok"},
            {"cancelButton", "annullamento"},
            {"filterButton", "filtro"},
            {"removeButton", "cancellare"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
