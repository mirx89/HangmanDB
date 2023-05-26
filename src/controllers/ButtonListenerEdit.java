package controllers;

import models.Database;
import models.DatabaseData;
import models.Model;
import views.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListenerEdit implements ActionListener {
    private Model model;
    private View view;
    public ButtonListenerEdit(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("Edit button works when clicked"); // test
        view.getBtnAdd().setEnabled(false); // Disable Add button
        view.getBtnUpdate().setEnabled(true);
        int id = (int) model.getTable().getModel().getValueAt(model.getTable().getSelectedRow(),3);
        List<DatabaseData> dd = new Database(model.getDatabaseFile(), model).selectById(id);
        view.getTxtWord().setText(dd.get(0).getWord());
        // view.getTxtNewCategory().setText(dd.get(0).getCategory());
        model.setChangeAbleId(id); // dd.get(0).getId()
        view.getCnbCategory().setSelectedItem(dd.get(0).getCategory());

    }
}
