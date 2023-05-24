package models;

import views.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyTable {
    private Model model;
    private View view;
    private String[] header = new String[] {"#", "Word", "Category", ""};
    private DefaultTableModel dtm = new DefaultTableModel(header,0);
    private JTable table = new JTable(dtm);

    public MyTable(Model model, View view) {
        this.model = model;
        this.view = view;
        model.setDtm(dtm);
        createTable();

        view.updateTable();
    }

    private void createTable() {
    JScrollPane jsp = new JScrollPane(table); //scrollbar for table
    view.getPanelBottom().revalidate();
    view.getPanelBottom().add(jsp);
    view.getPanelBottom().setVisible(false);
    view.getPanelBottom().setVisible(true);
    // Last Column is hidden
    table.getColumnModel().getColumn(3).setMinWidth(0);
    table.getColumnModel().getColumn(3).setMaxWidth(0);
    table.getColumnModel().getColumn(3).setWidth(0);
    // # is fixed width
    table.getColumnModel().getColumn(0).setMaxWidth(50);
    table.getColumnModel().getColumn(0).setMinWidth(50);
    model.setTable(table); // set table to model
    }
}
