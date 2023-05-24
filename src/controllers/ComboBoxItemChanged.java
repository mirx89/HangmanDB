package controllers;

import models.Database;
import models.Model;
import views.View;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxItemChanged implements ItemListener {
    private Model model;
    private View view;
    public ComboBoxItemChanged(Model model, View view) {
    this.model = model;
    this.view = view;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // JOptionPane.showMessageDialog(null, "Changed combo-box text"); // test
        if(e.getStateChange() == ItemEvent.SELECTED) {
            if(e.getItem().equals(model.getChooseCategory())) {
                // Choose a category
                view.getTxtNewCategory().setEnabled(true);
                new Database(model.getDatabaseFile(), model);
            }else {
                // Example Category "Car"
                view.getTxtNewCategory().setEnabled(false);
                view.getTxtNewCategory().setText("");
                new Database(model.getDatabaseFile(),model).selectByCategory(view.getCnbCategory().getSelectedItem().toString());
            }
            view.updateTable();
            view.getLblWordTotal().setText("Total: " + model.getDatabaseData().size());
        }
    }
}
