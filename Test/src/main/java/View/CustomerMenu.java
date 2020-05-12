package View;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.Accounts.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerMenu extends Menu {
    PurchaseMenu purchaseMenu = new PurchaseMenu(this);
    CartMenu cartMenu = new CartMenu(this);

    public CustomerMenu(Menu parentMenu) {
        super("Customer ", parentMenu);
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
            cartMenu.show();
            cartMenu.execute();
        } else if (input == 3) {
             purchaseMenu.show();
             purchaseMenu.execute();
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
        } else if (input == 7) {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void viewPersonalInfo() {
        String command;
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        customer.toString();
        while (true) {
            command = scanner.nextLine();
            Pattern editFieldPattern = Pattern.compile("edit\\s(.+)");
            Matcher editFieldMatcher = editFieldPattern.matcher(command);
            if (command.matches("edit\\s(.+)")) {
                editFieldMatcher.find();
                System.out.println("what is the new content for this field?");
                String newContent = scanner.nextLine();
                CustomerAbilitiesManager.changeField(customer, editFieldMatcher.group(1), newContent);
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("edit [field]");
                System.out.println("back");
            } else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }
    }

    public void viewOrders() {
        //nemidonam
    }

    public void viewBalance() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        System.out.println(customer.getCredit());
    }

    public void viewDiscountCodes() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        System.out.println(CustomerAbilitiesManager.showDiscountCodes(customer));
    }


}
