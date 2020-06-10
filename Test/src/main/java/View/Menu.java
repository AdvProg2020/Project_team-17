package View;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {

    private String name;
    protected HashMap<Integer, Menu> submenus;
    protected Menu parentMenu;
    public static Scanner scanner;
    protected static GridPane mainPane = new GridPane();
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

   /* public void show() {
        System.out.println(this.name + ":");
        for (Integer menuNum : submenus.keySet()) {
            System.out.println(menuNum + ". " + submenus.get(menuNum).getName());
        }
        if (this.parentMenu != null)
            System.out.println((submenus.size() + 1) + ". Back");
        else
            System.out.println((submenus.size() + 1) + ". Exit");
    }*/

    public void execute() {
        Menu nextMenu = null;
        try {
            int chosenMenu = Integer.parseInt(scanner.nextLine());
            if (chosenMenu == submenus.size() + 1) {
                if (this.parentMenu == null)
                    System.exit(1);
                else
                    nextMenu = this.parentMenu;
            } else
                nextMenu = submenus.get(chosenMenu);
            nextMenu.show();
            nextMenu.execute();
        } catch (Exception e) {
            System.out.println("enter a number");
            this.show();
            this.execute();
        }

    }

    public void adjustPane() {
        mainPane = new GridPane();
        HBox mainButtons = new HBox();
        Button loginButton = new Button("Login");
        Button userButton = new Button("Accounts");
        Button productButton = new Button("Products");
        Button offButton = new Button("Offs");
        Button exitButton = new Button("exit");
        mainButtons.getChildren().addAll(loginButton, userButton, productButton, offButton, exitButton);
        mainPane.add(mainButtons,0,0);
    }

    public void show() {
    }
}
