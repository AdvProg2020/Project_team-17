package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {

    private String name;
    protected HashMap<Integer, Menu> submenus;
    protected Menu parentMenu;
    public static Scanner scanner;
    protected static ArrayList<Menu> allMenus = new ArrayList<Menu>();

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        allMenus.add(this);
    }
    public static Menu getMenu(String name){
        for (Menu menu : allMenus) {
            if(menu.getName().equals(name)){
                return menu;
            }
        }return null;
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

    public void show() {
        System.out.println(this.name + ":");
        for (Integer menuNum : submenus.keySet()) {
            System.out.println(menuNum + ". " + submenus.get(menuNum).getName());
        }
        if (this.parentMenu != null)
            System.out.println((submenus.size() + 1) + ". Back");
        else
            System.out.println((submenus.size() + 1) + ". Exit");
    }

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
}
