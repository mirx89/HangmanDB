package models;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Model {
    private String databaseFile = "hangman_words_ee.db"; // default database file
    private List<DatabaseData> databaseData;
    private String[] cmbNames; // Combo-box names
    private String chooseCategory = "Choose a category";
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable table = new JTable(dtm);

    public Model(String databaseFile) {
        if(databaseFile != null) {
            this.databaseFile = databaseFile;
        }
        new Database(this.databaseFile, this); // "this" is model
    }

    public List<DatabaseData> getDatabaseData() {
        return databaseData;
    }

    public void setDatabaseData(List<DatabaseData> databaseData) {
        this.databaseData = databaseData;
    }

    public String[] getCmbNames() {
        return cmbNames;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public String getChooseCategory() {
        return chooseCategory;
    }

    public String getDatabaseFile() {
        return databaseFile;
    }

    public void setCorrectComboNames(List<String> unique) {
        cmbNames = new String[unique.size()+1];
        cmbNames[0] = chooseCategory; // "Choose a category"
        int x = 1;
        for (String s : unique) { // for(int x = 0; x<unique.size(); x++) unique.get(x)
            cmbNames[x] = s;
            x++;
        }
    }
}
