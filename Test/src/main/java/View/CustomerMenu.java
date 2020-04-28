package View;

import Controller.AccountsManager.CustomerAbilitiesManager;

import java.util.HashMap;

public class CustomerMenu extends Menu {
    private CustomerAbilitiesManager customerAbilitiesManager;

    public CustomerMenu(Menu parentMenu) {
        super("Customer Menu", parentMenu);


    }

    @Override
    public void show() {
        System.out.println("1.view personal info");
        System.out.println("2.view cart");
        System.out.println("3.purchase");
        System.out.println("4.view orders");
        System.out.println("5.view balance");
        System.out.println("6.view discount codes");
        System.out.println("7.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            viewPersonalInfo();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            viewCart();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 3) {
            purchase();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 4) {
            viewOrders();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 5) {
            viewBalance();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 6) {
            viewDiscountCodes();
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void viewPersonalInfo() {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.equals("view personal info")) {
                //username i ke login karde
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("edit [field]");
                System.out.println("back");
            } else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }
    }

    public void viewCart() {

    }

    public void purchase() {

    }

    public void viewOrders() {

    }

    public void viewBalance() {

    }

    public void viewDiscountCodes() {

    }


}
