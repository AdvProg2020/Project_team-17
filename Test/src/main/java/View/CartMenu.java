package View;


import Controller.CartManager;
import Models.Accounts.Customer;
import Models.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartMenu extends Menu {
    PurchaseMenu purchaseMenu = new PurchaseMenu(this);

    public CartMenu(Menu parentMenu) {
        super("Cart", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.show products");
        System.out.println("2.view product");
        System.out.println("3.increase product");
        System.out.println("4.decrease product");
        System.out.println("5.show total price");
        System.out.println("6.purchase");
        System.out.println("7.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            showProducts();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            viewProduct();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 3) {
            increaseProduct();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 4) {
            decreaseProduct();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 5) {
            showTotalPrice();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 6) {
            purchaseMenu.show();
            purchaseMenu.execute();
        } else if (input == 7) {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void showProducts() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        System.out.println(CartManager.showProducts(customer));
    }

    public void viewProduct() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern viewProductPattern = Pattern.compile("view product\\s(.+)");
            Matcher viewProductMatcher = viewProductPattern.matcher(command);
            if (command.matches("view product\\s(.+)")) {
                viewProductMatcher.find();
                try {
                    CartManager.isThereProductInThisCart(customer, Product.getProductWithId(viewProductMatcher.group(1)));
                    ProductMenu productMenu = new ProductMenu(this, Product.getProductWithId(viewProductMatcher.group(1)));
                    productMenu.show();
                    productMenu.execute();
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (command.matches("back")) {
                break;
            } else if (command.matches("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("view product [product ID]");
            }
        }

    }

    public void increaseProduct() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern increasePattern = Pattern.compile("increase\\s(.+)");
            Matcher increaseMatcher = increasePattern.matcher(command);
            if (command.matches("increase\\s(.+)")) {
                increaseMatcher.find();
                try {
                    CartManager.increaseProduct(customer, Product.getProductWithId(increaseMatcher.group(1)));
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("increase [product ID]");
            }
        }
    }

    public void decreaseProduct() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern decreasePattern = Pattern.compile("decrease\\s(.+)");
            Matcher decreaseMatcher = decreasePattern.matcher(command);
            if (command.matches("decrease\\s(.+)")) {
                decreaseMatcher.find();
                try {
                    CartManager.decreaseProduct(customer, Product.getProductWithId(decreaseMatcher.group(1)));
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("decrease [product ID]");
            }
        }
    }

    public void showTotalPrice() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        System.out.println(CartManager.showTotalPriceOfCart(customer));
    }

}
