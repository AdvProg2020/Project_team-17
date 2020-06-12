package View;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {

    private String name;
    protected HashMap<Integer, Menu> submenus;
    protected Menu parentMenu;
    public static Scanner scanner;

   protected static BorderPane mainPane = new BorderPane();
    protected static Stage window;
    protected static ArrayList<Menu> allMenus = new ArrayList<Menu>();

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        allMenus.add(this);
        if (scanner == null) {
            Menu.scanner = new Scanner(System.in);
        }
    }

    public static Menu getMenu(String name) {
        for (Menu menu : allMenus) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setSubmenus(HashMap<Integer, Menu> submenus) {
        this.submenus = submenus;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public static void setWindow(Stage window) {
        Menu.window = window;
    }

    public void setScene() {
        mainPane = new BorderPane();
        HBox mainButtons = new HBox(5);
        mainButtons.setSpacing(20);
        mainButtons.setAlignment(Pos.CENTER);
        Button accountsButton = new Button("Accounts");
        Button productButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        mainButtons.getChildren().addAll(accountsButton, productButton, discountButton, logoutButton);
        mainPane.setTop(mainButtons);
    }
    public void buttonAction(Button accountsButton , Button productsButton, Button discountButton , Button exitButton){
        accountsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleAccounts();
            }
        });
        productsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        discountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });

    }
    public void handleAccounts(){
        AccountsMenu accountsMenu = new AccountsMenu(this);
        accountsMenu.show();
    }



    public void show() {
    }
}
