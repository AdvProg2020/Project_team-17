package View;

import View.AccountMenus.CustomerMenu;
import View.AccountMenus.ManagerMenu;
import View.AccountMenus.SellerMenu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class MainMenu extends Menu {
    GridPane managerMainPane = new GridPane();

    public MainMenu() {
        super("Main Menu", null);
        HashMap<Integer, Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1, new RegisterAndLoginMenu(this));
        subMenus.put(2, new ManagerMenu(this));
        subMenus.put(3, new SellerMenu(this));
        subMenus.put(4, new CustomerMenu(this));
        subMenus.put(5, new ProductsMenu(this));
        subMenus.put(6, new DiscountsMenu(this));
        this.setSubmenus(subMenus);
    }

    public void adjustManagerPane() {
        Label test = new Label("test");
        managerMainPane.add(new HBox(test), 0, 7);
    }

    @Override
    public void adjustPane() {
        super.adjustPane();
        Label test = new Label("test");
        mainPane.add(new HBox(test), 0, 0);
    }

    @Override
    public void show() {
        if (RegisterAndLoginMenu.getCurrentManager() != null) {
            adjustPane();
            Scene scene = new Scene(mainPane, 600, 600);
            Menu.window.setScene(scene);
            Menu.window.show();
        } else {
            adjustManagerPane();
            Scene scene = new Scene(managerMainPane, 600, 600);
            Menu.window.setScene(scene);
            Menu.window.show();
        }
    }
}
