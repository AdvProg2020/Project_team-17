

        package View;

import Controller.AccountsManager.SellerAbilitiesManager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Product;
import Models.Request.AddProductRequest;
import Models.Request.EditProductRequest;
import View.RegisterAndLoginMenu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerMenu extends Menu {

    public SellerMenu(Menu parentMenu) {
        super("Seller ", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.view personal info");
        System.out.println("2.view company information");
        System.out.println("3.view sales history");
        System.out.println("4.manage products");
        System.out.println("5.add product");
        System.out.println("6.remove product");
        System.out.println("7.show categories");
        System.out.println("8.view offs");
        System.out.println("9.view balance");
        System.out.println("10.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            viewPersonalInfo();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            viewCompanyInformation();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 3) {
            viewSalesHistory();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 4) {
            manageProducts();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 5) {
            addProduct();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 6) {
            removeProduct();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 7) {
            showCategories();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 8) {
            viewOffs();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 9) {
            viewBalance();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 10) {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void viewPersonalInfo() {
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern editFieldPattern = Pattern.compile("edit\\s(.+)");
            Matcher editFieldMatcher = editFieldPattern.matcher(command);
            if (command.matches("edit\\s(.+)")) {
                editFieldMatcher.find();
                System.out.println("what is the new content for this field?");
                String newContent = scanner.nextLine();
                SellerAbilitiesManager.changeField(seller, editFieldMatcher.group(1), newContent);
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("edit [field]");
                System.out.println("back");
            } else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }
    }

    public void viewCompanyInformation() {
        System.out.println(RegisterAndLoginMenu.currentSeller.getCompanyName());
    }

    public void viewSalesHistory() {
        //nafahmidam che field ro bayad neshon bede
    }
    public void manageProducts() {
        String command;
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        System.out.println(seller.getAllProducts());
        while (true) {
            command = scanner.nextLine();
            Pattern viewProductPattern = Pattern.compile("view\\s(.+)");
            Matcher viewProductMatcher = viewProductPattern.matcher(command);
            Pattern editProductPattern = Pattern.compile("edit\\s(.+)");
            Matcher editProductMatcher = editProductPattern.matcher(command);
            Pattern viewBuyersPattern = Pattern.compile("view buyers\\s(.+)");
            Matcher viewBuyersMatcher = viewBuyersPattern.matcher(command);
            if (command.matches("view\\s(.+)")) {
                viewProductMatcher.find();
                try {
                    SellerAbilitiesManager.checkProductByID(viewProductMatcher.group(1));
                    Product product = Product.getProductWithId(viewProductMatcher.group(1));
                    System.out.println(product.toString());
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (command.matches("edit\\s(.+)")) {
                editProductMatcher.find();
                try {
                    SellerAbilitiesManager.checkProductByID(editProductMatcher.group(1));
                    System.out.println("enter the field you want to change: ");
                    String field = scanner.nextLine();
                    System.out.println("enter the content for this field: ");
                    String newContent = scanner.nextLine();
                    SellerAbilitiesManager.editProduct(editProductMatcher.group(1), field, newContent);
                    new EditProductRequest(editProductMatcher.group(1), seller, RegisterAndLoginMenu.getCurrentManager(), Product.getProductWithId(editProductMatcher.group(1)));
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (command.matches("view buyers\\s(.+)")) {
                viewBuyersMatcher.find();
                try {
                    SellerAbilitiesManager.checkProductByID(viewBuyersMatcher.group(1));
                    //TODO bayad inja ro bezanim k buyers ro neshon bede
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("edit [productID]");
                System.out.println("view [productID]");
                System.out.println("view buyers [productID]");
                System.out.println("back");

            }
        }

    }

    public void addProduct() {
        String command;
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        while (true) {
            command = scanner.nextLine();
            if (command.equals("add product")) {
                System.out.println("Enter productID: ");
                String id = scanner.nextLine();
                System.out.println("Enter product status: ");
                ProductEnum productStatus = ProductEnum.valueOf(scanner.nextLine());
                System.out.println("Enter product name: ");
                String name = scanner.nextLine();
                System.out.println("Enter company name: ");
                String companyName = scanner.nextLine();
                System.out.println("Enter product price: ");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter product Category: ");
                Category category = Category.getCategoryByName(scanner.nextLine());
                System.out.println("Enter explanation for product: ");
                String explanation = scanner.nextLine();
                Product product = SellerAbilitiesManager.addProduct(id, productStatus, name, companyName, price, category, seller, explanation);
                new AddProductRequest("random id", seller, RegisterAndLoginMenu.getCurrentManager(), product, category);
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("add product");
                System.out.println("back");
            }
        }

    }

    public void removeProduct() {
        String command;
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        while (true) {
            command = scanner.nextLine();
            Pattern removeProductPattern = Pattern.compile("remove product\\s(.+)");
            Matcher removeProductMatcher = removeProductPattern.matcher(command);
            if (command.matches("remove product\\s(.+)")) {
                removeProductMatcher.find();
                try {
                    SellerAbilitiesManager.removeProduct(seller, removeProductMatcher.group(1));
                    System.out.println("product removed successfully");
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("remove product [productID]");
                System.out.println("back");
            }
        }
    }

    public void showCategories() {
        System.out.println(SellerAbilitiesManager.showCategories());
    }

    public void viewOffs() {
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        SellerAbilitiesManager.viewOffs(seller);
    }

    public void viewBalance() {
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        System.out.println(SellerAbilitiesManager.viewBalance(seller));
    }
}