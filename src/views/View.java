package views;

import models.DatabaseData;
import models.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class View extends JFrame {
    private Model model;
    private PanelTop panelTop;
    private PanelBottom panelBottom;

    public View(Model model) {
        this.model = model;
        setupFrame();
        setupPanels();
    }

    private void setupFrame() {
        this.setTitle("Manage Hangman Database"); // This is JFrame
        // setTitle("Manage Hangman Database"); // Same as upper row
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close window. kohtustuslik! Muidu ainult peidab akna
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(520,480));
    }
    private void setupPanels() {
        panelTop = new PanelTop(model); // create panel top
        panelBottom = new PanelBottom(); // Create bottom panel
        this.add(panelTop, BorderLayout.NORTH); // Add panel top on JFrame
        this.add(panelBottom,BorderLayout.CENTER); // Add panel bottom on JFrame

    }
    public JLabel getLblWordTotal() {
        return panelTop.getLblWordTotal();
    }

    public JLabel getLblCategoryTotal() {
        return panelTop.getLblCategoryTotal();
    }

    public JTextField getTxtWord() {
        return panelTop.getTxtWord();
    }

    public JTextField getTxtNewCategory() {
        return panelTop.getTxtNewCategory();
    }

    public JButton getBtnAdd() {
        return panelTop.getBtnAdd();
    }

    public JButton getBtnEdit() {
        return panelTop.getBtnEdit();
    }

    public JButton getBtnUpdate() {
        return panelTop.getBtnUpdate();
    }

    public JComboBox<String> getCnbCategory() {return panelTop.getCnbCategory();}

    public PanelBottom getPanelBottom() {return panelBottom;}

    public void updateTable() {
        model.getDtm().setRowCount(0); // Clear table
        int x = 1;
        for(DatabaseData dd : model.getDatabaseData()) {
            String word = dd.getWord();
            String category = dd.getCategory();
            model.getDtm().addRow(new Object[] {x, word, category, dd.getId()});
            x++;
        }
    }
    public void updateComboBox() {
        getCnbCategory().removeAllItems(); // Clear combo-box
        for(String category : model.getCmbNames()) {
            getCnbCategory().addItem(category);
        }
    }
    public void registerItemStateChanged(ItemListener e) {
        panelTop.getCnbCategory().addItemListener(e);
    }
    public void registerButtonAdd(ActionListener al) {
        panelTop.getBtnAdd().addActionListener(al);
    }
}
