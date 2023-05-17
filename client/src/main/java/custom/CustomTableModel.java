package custom;

import element.CollectionPart;
import general.GeneralVars;
import general.validator.GeneralValidator;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CustomTableModel extends AbstractTableModel {
    private List<Object[]> data = new ArrayList<>();
    private List<Object[]> dataCopy;
    private final String[] headers = new String[]{"id", "name", "x", "y", "health", "heart count", "category", "weapon", "chapter name", "marines count", "creation date", "owner"};
    private final HashMap<Integer, Object[]> backUp = new HashMap<>();
    private final Set<Integer> notEditableFields = new HashSet<>();
    private String curUser;
    private final GeneralValidator validator = new GeneralValidator();
    private final JLabel warnMsg;
    private int sortedColumn = -1;
    private SortOrder sortOrder = SortOrder.ASCENDING;
    private final HashMap<Integer, String> filteredFields = new HashMap<>();

    public CustomTableModel(JLabel warnMsg){
        this.warnMsg = warnMsg;
        notEditableFields.add(0);
        notEditableFields.add(GeneralVars.VAR_COUNT - 1);
        notEditableFields.add(GeneralVars.VAR_COUNT - 2);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return GeneralVars.VAR_COUNT;
    }

    @Override
    public String getColumnName(int index){
        return headers[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }
    private void clearBackUp() {
        this.backUp.clear();
    }
    public void saveChanges(){
        clearBackUp();
    }
    public void returnToBackUp(){
        for (Map.Entry<Integer, Object[]> entry : backUp.entrySet()) {
            data.set(entry.getKey(), entry.getValue());
        }
        this.fireTableDataChanged();
        clearBackUp();
    }
    public Set<Integer> getChangedRows() {
        return backUp.keySet();
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return (data.get(rowIndex)[GeneralVars.VAR_COUNT - 1].toString().equals(curUser) && !notEditableFields.contains(columnIndex));
    }
    public void addData(CollectionPart elem){
        Object[] fields = new Object[GeneralVars.VAR_COUNT];
        fields[0] = elem.getId();
        fields[1] = elem.getName();
        fields[2] = elem.getCoordinates().getX();
        fields[3] = elem.getCoordinates().getY();
        fields[4] = elem.getHealth();
        fields[5] = elem.getHeartCount();
        fields[6] = elem.getCategory();
        fields[7] = elem.getMeleeWeapon();
        fields[8] = elem.getChapter().getName();
        fields[9] = elem.getChapter().getMarinesCount();
        fields[10] = elem.getCreationDate();
        fields[11] = elem.getOwner();
        data.add(fields);
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        try{
            if (!getChangedRows().contains(rowIndex)) {
                if (aValue != null){
                    validateInput(columnIndex, aValue.toString());
                } else {
                    validateInput(columnIndex, "");
                }
                backUp.put(rowIndex, data.get(rowIndex).clone());
            }
            data.get(rowIndex)[columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        } catch (Exception e){
            warnMsg.setText(e.getMessage());
        }
    }
    public void clearData(){
        data.clear();
    }
    public void removeRow(int row){
        if (data.get(row)[GeneralVars.VAR_COUNT - 1].toString().equals(curUser)){
            data.remove(row);
            this.fireTableDataChanged();
        }
    }

    public void setCurUser(String curUser) {
        this.curUser = curUser;
    }
    public Object[] getRow(int row){
        return data.get(row);
    }
    public void filter(int column, String namePart, boolean fromGui){
        if (fromGui && dataCopy == null){
            dataCopy = new ArrayList<>(data);
        }
        if (fromGui){
            filteredFields.put(column, namePart);
        }
        data = data.stream()
                .filter(o -> o[column] != null && o[column].toString().contains(namePart))
                .collect(Collectors.toList());
        if (fromGui){
            this.fireTableDataChanged();
        }
    }
    public void cancelFilter(){
        filteredFields.clear();
        data = new ArrayList<>(dataCopy);
        dataCopy = null;
        this.fireTableDataChanged();
    }
    public void sort(int column, boolean doubleClick){
        if (column == sortedColumn && doubleClick){
            if (sortOrder == SortOrder.ASCENDING){
                sortOrder = SortOrder.DESCENDING;
            } else {
                sortOrder = SortOrder.ASCENDING;
            }
        } else {
            sortedColumn = column;
            sortOrder = SortOrder.ASCENDING;
        }
        Comparator<Object[]> cmp = getComparator(column);
        if (sortOrder == SortOrder.ASCENDING){
            data = data.stream()
                    .sorted(cmp)
                    .collect(Collectors.toList());
        } else {
            data = data.stream()
                    .sorted(cmp.reversed())
                    .collect(Collectors.toList());
        }
        if(doubleClick){
            this.fireTableDataChanged();
        }
    }
    public void validateInput(int column,String line){
        switch (column){
            case 1 -> validator.validateName(line);
            case 2 -> validator.validateX(line);
            case 3 -> validator.validateY(line);
            case 4 -> validator.validateHealth(line);
            case 5 -> validator.validateHeartCount(line);
            case 6 -> validator.validateCategory(line);
            case 7 -> validator.validateMeleeWeapon(line);
            case 8 -> validator.validateChapterName(line);
            case 9 -> validator.validateMarinesCount(line);
        }
    }
    private Comparator<Object[]> getComparator(int column){
        return switch (column){
            case 0 -> Comparator.nullsFirst(Comparator.comparing(o -> (o[column] != null) ? Long.parseLong(o[column].toString()) : null, Comparator.nullsFirst(Long::compareTo)));
            case 2, 3, 5, 9 -> Comparator.nullsFirst(Comparator.comparing(o -> (o[column] != null) ? Integer.parseInt(o[column].toString()) : null, Comparator.nullsFirst(Integer::compareTo)));
            case 4 -> Comparator.nullsFirst(Comparator.comparing(o -> (o[column] != null) ? Double.parseDouble(o[column].toString()) : null, Comparator.nullsFirst(Double::compareTo)));
            case 10 -> Comparator.nullsFirst(Comparator.comparing(o -> {
                try {
                    return (o[column] != null) ? new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH).parse(o[column].toString()) : null;
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }));
            default -> Comparator.nullsFirst(Comparator.comparing(o -> (o[column] != null) ? String.valueOf(o[column]) : null, Comparator.nullsFirst(String::compareTo)));
        };
    }

    public int getSortedColumn(){
        return sortedColumn;
    }
    public HashMap<Integer, String> getFilteredFields(){
        return filteredFields;
    }
    public void setDataCopy(){
        this.dataCopy = new ArrayList<>(data);
    }
    public void clearTableMod(){
        filteredFields.clear();
        dataCopy = null;
        sortedColumn = -1;
        sortOrder = SortOrder.ASCENDING;
    }
}
