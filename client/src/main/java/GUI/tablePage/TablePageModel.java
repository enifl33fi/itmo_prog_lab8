package GUI.tablePage;

import custom.*;
import element.CollectionPart;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;

public class TablePageModel extends JFrame{
    private JPanel tablePagePanel;
    private CommandPanel commandPanel;
    private CustomUserZone customUserZone;
    private JButton visualizationButton;
    private JTable dataTable;
    private JButton cancelButton;
    private JButton okButton;
    private JButton filterButton;
    private JButton removeButton;
    private JScrollPane tableScroll;
    private JLabel warnMsg;
    private JTextField namePartField;
    private JButton backButton;
    private CustomTableModel customTableModel;
    private final TablePageLogic logic;
    private boolean isEditingMode = false;
    public TablePageModel(TablePageLogic tablePageLogic){
        this.logic = tablePageLogic;
        customUserZone.setBackground(tablePagePanel.getBackground());
        customUserZone.setComponentBackground(tablePagePanel.getBackground());
        warnMsg.setForeground(Color.RED);
        setSize(800, 600);
        okButton.setVisible(false);
        cancelButton.setVisible(false);
        customTableModel = new CustomTableModel(warnMsg);
        dataTable.setModel(customTableModel);
        dataTable.setColumnSelectionAllowed(true);
        dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dataTable.setRowSorter(new CustomRowSorter<>(customTableModel));
        customTableModel.addTableModelListener(e -> {
            if (e.getColumn() != -1){
                if (!isEditingMode){
                    isEditingMode = true;
                    logic.setTableUpdate(false);
                    setComponentsAccess();
                }
            }
        });
        logic.setSettings(this, tablePagePanel, "table");
        commandPanel.setBackground(tablePagePanel.getBackground());
        for (CommandButton commandButton: commandPanel.getContainedButtons()){
            commandButton.addActionListener((e) -> logic.execute(commandButton));
        }
        visualizationButton.setBackground(Color.LIGHT_GRAY);
        visualizationButton.setForeground(Color.WHITE);
        customUserZone.getHelpButton().addActionListener((e) -> logic.help());
        customUserZone.getLogOutButton().addActionListener((e) -> logic.goBack());
        removeButton.addActionListener((e) -> {
            int row = dataTable.getSelectedRow();
            if (row != -1){
                logic.doRemove(customTableModel.getRow(row)[0].toString());
            }
        });
        filterButton.addActionListener(e -> {
            int column = dataTable.getSelectedColumn();
            String givenNamePart = namePartField.getText();
            namePartField.setText("");
            if (column != -1){
                customTableModel.filter(column, givenNamePart, true);
            }
        });
        backButton.addActionListener(e -> customTableModel.cancelFilter());
        okButton.addActionListener((e) -> {
            if (dataTable.isEditing()) {
                dataTable.getCellEditor().stopCellEditing();
            }
            warnMsg.setText(" ");
            logic.applyChanges(customTableModel.getChangedRows());
            customTableModel.saveChanges();
            isEditingMode = false;
            logic.setTableUpdate(true);
            setComponentsAccess();
        });
        cancelButton.addActionListener((e) -> {
            if (dataTable.isEditing()) {
                dataTable.getCellEditor().stopCellEditing();
            }
            customTableModel.returnToBackUp();
            isEditingMode = false;
            logic.setTableUpdate(true);
            setComponentsAccess();
        });
        visualizationButton.addActionListener((e) -> logic.goFurther());
        dataTable.getTableHeader().setReorderingAllowed(false);
    }
    private void createUIComponents() {
        commandPanel = new CommandPanel();
        customUserZone = new CustomUserZone();
    }
    protected JPanel getPanel(){
        return tablePagePanel;
    }
    protected void setUserName(String login){
        customUserZone.setUserName(login);
        customTableModel.setCurUser(login);
    }
    private void setComponentsAccess(){
        removeButton.setVisible(!isEditingMode);
        filterButton.setVisible(!isEditingMode);
        namePartField.setVisible(!isEditingMode);
        backButton.setVisible(!isEditingMode);
        customUserZone.getLogOutButton().setEnabled(!isEditingMode);
        visualizationButton.setEnabled(!isEditingMode);
        cancelButton.setVisible(isEditingMode);
        okButton.setVisible(isEditingMode);
    }
    protected void setData(LinkedList<CollectionPart> data){
        customTableModel.clearData();
        data.forEach(customTableModel::addData);
        if (customTableModel.getSortedColumn() != -1){
            customTableModel.sort(customTableModel.getSortedColumn(), false);
        }
        if (customTableModel.getFilteredFields().size() != 0){
            customTableModel.setDataCopy();
            for (Map.Entry<Integer, String> entry:customTableModel.getFilteredFields().entrySet()){
                customTableModel.filter(entry.getKey(), entry.getValue(), false);
            }
        }
        customTableModel.fireTableDataChanged();
    }
    protected Object[] getChangedElements(int row){
        return customTableModel.getRow(row);
    }
    protected void clearSortAndFilter(){
        customTableModel.clearTableMod();
    }
    public void switchLanguage(){
        ResourceBundle r = ResourceBundle.getBundle("GUI.bundles.TablePage");
        fillLabels(r);

    }
    private void fillLabels(ResourceBundle r){
        cancelButton.setText(r.getString("cancelButton"));
        okButton.setText(r.getString("okButton"));
        filterButton.setText(r.getString("filterButton"));
        removeButton.setText(r.getString("removeButton"));
        backButton.setText(r.getString("backButton"));
        visualizationButton.setText(r.getString("visualizationButton"));

    }
}
