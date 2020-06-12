package View.AccountMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.Accounts.Customer;
import Models.Product;
import View.CartMenu;
import View.Menu;
import View.RegisterCustomerMenu;
import View.RegisterSellerMenu;
import View.PurchasingProcessMenus.PurchaseMenu;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerMenu extends Menu {
    PurchaseMenu purchaseMenu = new PurchaseMenu(this);
    CartMenu cartMenu = new CartMenu(this);
    Menu menu = Menu.getMenu("Main Menu");
    Menu registerAndLoginMenu = Menu.getMenu("Register And Login ");

    public CustomerMenu(Menu parentMenu) {
        super("Customer ", parentMenu);
    }

    @Override
    public void show(){
        if (RegisterCustomerMenu.getCurrentCustomer() != null) {
            System.out.println("1.view personal info");
            System.out.println("2.view cart");
            System.out.println("3.purchase");
            System.out.println("4.view orders");
            System.out.println("5.view balance");
            System.out.println("6.view discount codes");
            System.out.println("7.logout");
            System.out.println("8.back");
        } else {
            System.out.println("you haven't logged in yet, first login as a customer");
            registerAndLoginMenu.setParentMenu(this);
            registerAndLoginMenu.show();
        }
    }



    //check
    public void viewPersonalInfo() {
        String command;
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        System.out.println(customer.toString());
        while (true) {
            command = scanner.nextLine();
            Pattern editFieldPattern = Pattern.compile("edit\\s(.+)");
            Matcher editFieldMatcher = editFieldPattern.matcher(command);
            if (command.matches("edit\\s(.+)")) {
                editFieldMatcher.find();
                System.out.println("what is the new content for this field?");
                String newContent = scanner.nextLine();
                System.out.println(CustomerAbilitiesManager.changeField(customer, editFieldMatcher.group(1), newContent));
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
        String command;
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        try {
            System.out.println(CustomerAbilitiesManager.viewOrders(customer));
            while (true) {
                command = scanner.nextLine();
                Pattern showOrderPattern = Pattern.compile("show order\\s(.+)");
                Matcher showOrderMatcher = showOrderPattern.matcher(command);
                Pattern ratePattern = Pattern.compile("rate\\s(.+)\\s(\\d)");
                Matcher rateMatcher = ratePattern.matcher(command);
                if (command.matches("show order\\s(.+)")) {
                    showOrderMatcher.find();
                    try {
                        System.out.println(CustomerAbilitiesManager.showOrder(showOrderMatcher.group(1)));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.matches("rate\\s(.+)\\s(\\d)")) {
                    rateMatcher.find();
                    double rate = Double.parseDouble(rateMatcher.group(2));
                    if (rate > 5) {
                        System.out.println("you can only rate in range 1-5 ! enter new score: ");
                        rate = scanner.nextDouble();
                    }
                    try {
                        CustomerAbilitiesManager.rateProduct(customer, Product.getProductWithId(rateMatcher.group(1)), rate);
                        System.out.println("you rated successfully");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.equals("help")) {
                    System.out.println("commands that you can enter are:");
                    System.out.println("show order [orderID]");
                    System.out.println("rate [productID] [1-5]");
                    System.out.println("back");
                } else if (command.equals("back")) {
                    break;
                } else System.out.println("Command is invalid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //check
    public void viewBalance() {
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        System.out.println(customer.getCredit());
    }

    //check
    public void viewDiscountCodes() {
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        try {
            System.out.println(CustomerAbilitiesManager.showDiscountCodes(customer));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}