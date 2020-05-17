package View;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.Accounts.Customer;
import Models.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerMenu extends Menu {
    PurchaseMenu purchaseMenu = new PurchaseMenu(this);
    CartMenu cartMenu = new CartMenu(this);
    RegisterAndLoginMenu registerAndLoginMenu = new RegisterAndLoginMenu(this);
    CommandProcessor commandProcessor = new CommandProcessor();


    public CustomerMenu(Menu parentMenu) {
        super("Customer ", parentMenu);
    }

    @Override
    public void show() {
        if (RegisterAndLoginMenu.getCurrentCustomer() != null) {
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
            registerAndLoginMenu.show();
            registerAndLoginMenu.execute();
        }
    }

    @Override
    public void execute() {
        try {
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
                RegisterAndLoginMenu.logout();
                commandProcessor.runWithMenu();
            }else if(input == 8){
                parentMenu.show();
                parentMenu.execute();
            }

        } catch (Exception e) {
            System.out.println("you should enter a number");
        }

    }

    public void viewPersonalInfo() {
        String command;
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        System.out.println(customer.toString());
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
        String command;
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
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
        }catch (Exception e){
        System.out.println(e.getMessage());
        }
    }

    public void viewBalance() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        System.out.println(customer.getCredit());
    }

    public void viewDiscountCodes() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        try {
            System.out.println(CustomerAbilitiesManager.showDiscountCodes(customer));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}