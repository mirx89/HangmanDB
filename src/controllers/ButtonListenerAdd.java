package controllers;

import models.Database;
import models.Model;
import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerAdd implements ActionListener {
    private Model model;
    private View view;
    public ButtonListenerAdd(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("Clicked Add"); // test
        String newWord = view.getTxtWord().getText().trim();
        // Selected category
        String category = view.getCnbCategory().getSelectedItem().toString();
        // Selected category index
        int categoryIndex = view.getCnbCategory().getSelectedIndex();
        // New category
        String newCategory = view.getTxtNewCategory().getText().trim();
        if(!newWord.isEmpty() && categoryIndex == 0 && !newCategory.isEmpty()){
            // New word, new category
            new Database(model.getDatabaseFile(), model).insert(newWord, newCategory);
        } else if(!newWord.isEmpty() && categoryIndex > 0 && newCategory.isEmpty()) {
            // New word, old category
            new Database(model.getDatabaseFile(), model).insert(newWord, category);
        } else {
            JOptionPane.showMessageDialog(view, "FAILED!!!\nSomething is missing or wrong!", "warning", JOptionPane.ERROR_MESSAGE);
        }
        clearForm();
        view.updateComboBox();
        view.getLblWordTotal().setText("Total: " + model.getDatabaseData().size());
        view.getLblCategoryTotal().setText("Total: " + (model.getCmbNames().length-1));
        view.updateTable(); // Update table in form

    }
    private void clearForm() {
        view.getTxtWord().setText(""); // clear new word
        view.getTxtNewCategory().setText(""); // Clear new category
        view.getCnbCategory().setSelectedIndex(0); // Set combo-box to first
    }
}
