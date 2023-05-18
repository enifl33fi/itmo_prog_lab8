package GUI.bundles;

import java.util.ListResourceBundle;

public class TablePage_es_SV extends ListResourceBundle {
    private Object[][] contents = {{"backButton", "volver"},
            {"visualizationButton", "visua"},
            {"okButton", "ok"},
            {"cancelButton", "cancelación"},
            {"filterButton", "filtro"},
            {"removeButton", "borrar"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
