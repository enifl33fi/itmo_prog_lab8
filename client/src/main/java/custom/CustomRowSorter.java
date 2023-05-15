package custom;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CustomRowSorter<M extends TableModel> extends TableRowSorter<M> {
    public CustomRowSorter(M model){
        super(model);
    }
    @Override
    public void toggleSortOrder(int column){
        getTableModel().sort(column, true);
    }
    public CustomTableModel getTableModel(){
        return (CustomTableModel) this.getModel();
    }
}
