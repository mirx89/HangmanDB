package app;

import controllers.Controller;
import models.Model;
import views.View;

public class MainApp {

    private static void app(String databaseFile){
        Model model = new Model(databaseFile);
        View view = new View(model);
        new Controller(model, view);

        view.pack();
        view.setLocationRelativeTo(null); //JFrame center to screen
        view.setVisible(true); // Show main window
    }

    public static void main(String[] args) {
        String databaseFile = null;
        if(args.length>=1) {
            databaseFile = args[0];
        }
        app(databaseFile); // default database null
    }
}
