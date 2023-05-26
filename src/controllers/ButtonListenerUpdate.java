package controllers;

import models.Database;
import models.Model;
import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ButtonListenerUpdate implements ActionListener {
    private final Model model;
    private final View view;
    public ButtonListenerUpdate(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("Update button works"); // test
        String word = view.getTxtWord().getText();
        String category = Objects.requireNonNull(view.getCnbCategory().getSelectedItem()).toString();
        String newCategory = view.getTxtNewCategory().getText();
        if (!word.isEmpty() && view.getCnbCategory().getSelectedItem().toString().equals(model.getChooseCategory()) && !newCategory.isEmpty()) {
            // new category
            Database db = new Database(model.getDatabaseFile(), model);
            db.update(model.getChangeAbleId(),word, newCategory);
        } else if (!word.isEmpty() && !view.getCnbCategory().getSelectedItem().toString().equals(model.getChooseCategory()) && newCategory.isEmpty()) {
            // old category
            Database db = new Database(model.getDatabaseFile(), model);
            db.update(model.getChangeAbleId(), word, category);
        } else {
            JOptionPane.showMessageDialog(view, "Something went wrong. Try again");
        }
        view.updateTable();
        view.updateComboBox();
        view.getBtnEdit().setEnabled(false);
        view.getBtnUpdate().setEnabled(false);
        view.getBtnAdd().setEnabled(true); // Add enabled button
        view.getTxtWord().setText("");
        view.getTxtNewCategory().setText("");
    }
}
