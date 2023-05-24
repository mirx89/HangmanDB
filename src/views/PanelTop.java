package views;

import models.Model;

import javax.swing.*;
import java.awt.*;

public class PanelTop extends JPanel {
    private Model model;
    private JPanel pnlComponents = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();
    private JLabel lblWordTotal, lblCategoryTotal;
    private JTextField txtWord, txtNewCategory;
    private JButton btnAdd, btnEdit, btnUpdate;
    private JComboBox<String> cnbCategory;

    public PanelTop(Model model) {
        this.model = model;
        setupPanel(); // Setup PanelTop
        setupConstraints(); // settings for cell
        setupLineOne(); // First line on pnlComponents
        setupLineTwo();
        setupLineThree();
        setupLineFour();

        this.add(pnlComponents); //add pnlComponents on Panel top
    }

    private void setupPanel() {
        this.setBackground(new Color(250,245,215));
        this.setLayout(new FlowLayout(FlowLayout.LEFT)); // Can use also 0 as LEFT
    }

    private void setupConstraints() {
        gbc.anchor = GridBagConstraints.WEST; // Cell contents align left
        gbc.insets = new Insets(2,2,2,2); // All around 2px
        gbc.fill = GridBagConstraints.BOTH; // Stretches the contents a cell to a width
    }
    private void setupLineOne() {
        JLabel lblWord = new JLabel("Word");
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        pnlComponents.add(lblWord,gbc); // on pnlComponents lblWord and settings

        txtWord = new JTextField("",14);
        gbc.gridx = 1; // Column
        gbc.gridy = 0; // Row
        pnlComponents.add(txtWord,gbc); // on pnlComponents txtWord and settings

        lblWordTotal = new JLabel("Total: " + model.getDatabaseData().size());
        gbc.gridx = 2; // Column
        gbc.gridy = 0; // Row
        pnlComponents.add(lblWordTotal,gbc); // on pnlComponents lblWordTotal and settings
    }
    private void setupLineTwo(){
        JLabel lblCategory = new JLabel("Category");
        gbc.gridx = 0; // Column
        gbc.gridy = 1; // Row
        pnlComponents.add(lblCategory,gbc); // on pnlComponents lblCategory and settings

        cnbCategory = new JComboBox<>(model.getCmbNames()); // combo-box
        gbc.gridx = 1; // Column
        gbc.gridy = 1; // Row
        pnlComponents.add(cnbCategory,gbc); // on pnlComponents txtWord and settings

        lblCategoryTotal = new JLabel("Total: " + (model.getCmbNames().length-1));
        gbc.gridx = 2; // Column
        gbc.gridy = 1; // Row
        pnlComponents.add(lblCategoryTotal,gbc); // on pnlComponents lblWordTotal and settings
    }
    private void setupLineThree() {
        JLabel lblNewCategory = new JLabel("New Category");
        gbc.gridx = 0; // Column
        gbc.gridy = 2; // Row
        pnlComponents.add(lblNewCategory,gbc); // on pnlComponents lblNewCategory and settings

        txtNewCategory = new JTextField("",14);
        gbc.gridx = 1; // Column
        gbc.gridy = 2; // Row
        pnlComponents.add(txtNewCategory,gbc); // on pnlComponents txtWord and settings

    }
    private void setupLineFour() {
        btnEdit = new JButton("Edit Record");
        btnEdit.setEnabled(false);
        gbc.gridx = 0; // Column
        gbc.gridy = 3; // Row
        pnlComponents.add(btnEdit,gbc); // on pnlComponents btnEdit and settings

        btnAdd = new JButton("Add new Word");
        gbc.gridx = 1; // Column
        gbc.gridy = 3; // Row
        pnlComponents.add(btnAdd,gbc); // on pnlComponents btnEdit and settings

        btnUpdate = new JButton("Update record");
        btnUpdate.setEnabled(false);
        gbc.gridx = 2; // Column
        gbc.gridy = 3; // Row
        pnlComponents.add(btnUpdate,gbc); // on pnlComponents btnUpdate and settings
    }

    public JLabel getLblWordTotal() {
        return lblWordTotal;
    }

    public JLabel getLblCategoryTotal() {
        return lblCategoryTotal;
    }

    public JTextField getTxtWord() {
        return txtWord;
    }

    public JTextField getTxtNewCategory() {
        return txtNewCategory;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JComboBox<String> getCnbCategory() {
        return cnbCategory;
    }
}
