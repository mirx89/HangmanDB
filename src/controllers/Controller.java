package controllers;

import models.Model;
import models.MyTable;
import views.View;

public class Controller {
    public Controller(Model model, View view) {
        new MyTable(model, view);
        view.registerItemStateChanged(new ComboBoxItemChanged(model, view));
        view.registerButtonAdd(new ButtonListenerAdd(model, view));
        view.registerButtonEdit(new ButtonListenerEdit(model, view));
        view.registerButtonUpdate(new ButtonListenerUpdate(model, view));

    }

}
