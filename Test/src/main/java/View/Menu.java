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
    }

    public void show() {
    }
}
