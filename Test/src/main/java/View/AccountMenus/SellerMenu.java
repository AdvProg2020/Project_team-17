package View.AccountMenus;

import Controller.AccountsManager.SellerAbilitiesManager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Discount;
import Models.Product;
import Models.Request.AddOffRequest;
import Models.Request.AddProductRequest;
import View.Menu;
import View.RegisterAndLoginMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerMenu extends Menu {
    //RegisterAndLoginMenu registerAndLoginMenu = new RegisterAndLoginMenu(this);
    //CommandProcessor commandProcessor = new CommandProcessor();
    Menu menu = Menu.getMenu("Main Menu");
    Menu registerAndLoginMenu = Menu.getMenu("Register And Login ");

    public SellerMenu(Menu parentMenu) {
        super("Seller ", parentMenu);
    }

    @Override
    public void show() {
        if (RegisterAndLoginMenu.getCurrentSeller() != null) {
            System.out.println("1.view personal info");
            System.out.println("2.view company information");
            System.out.println("3.view sales history");
            System.out.println("4.manage products");
            System.out.println("5.add product");
            System.out.println("6.remove product");
            System.out.println("7.show categories");
            System.out.println("8.view offs");
            System.out.println("9.view balance");
            System.out.println("10.logout");
            System.out.println("11.back");
        } else {
            System.out.println("you haven't logged in yet, first login as a seller");
            registerAndLoginMenu.setParentMenu(this);
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
                RegisterAndLoginMenu.logout();
                //commandProcessor.runWithMenu();
                menu.show();
                menu.execute();
            } else if (input == 11) {
                parentMenu.show();
                parentMenu.execute();
            }
        } catch (Exception e) {
            System.out.println("you should enter a number");
            this.show();
            this.execute();
        }
    }

    public void viewPersonalInfo() {
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        System.out.println(seller.toString());
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

    //check
    public void viewCompanyInformation() {
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        System.out.println(SellerAbilitiesManager.viewFactoryInfo(seller));
    }

    //check
    public void viewSalesHistory() {
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        try {
            System.out.println(SellerAbilitiesManager.viewSalesHistory(seller));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //check
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
            if (command.matches("view buyers\\s(.+)")) {
                viewBuyersMatcher.find();
                System.out.println(SellerAbilitiesManager.viewProductsBuyer(seller, viewBuyersMatcher.group(1)));
            } else if (command.matches("edit\\s(.+)")) {
                editProductMatcher.find();
                try {
                    SellerAbilitiesManager.checkProductByID(editProductMatcher.group(1));
                    System.out.println("enter the field you want to change: ");
                    String field = scanner.nextLine();
                    System.out.println("enter the content for this field: ");
                    String newContent = scanner.nextLine();
                    SellerAbilitiesManager.sendEditingProductRequest(editProductMatcher.group(1), seller, field, newContent);
                    System.out.println("request sent to manager");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("view\\s(.+)")) {
                viewProductMatcher.find();
                try {
                    SellerAbilitiesManager.checkProductByID(viewProductMatcher.group(1));
                    Product product = Product.getProductWithId(viewProductMatcher.group(1));
                    System.out.println(product.toString());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
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

    //check
    public void addProduct() {
        String command;
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        while (true) {
            command = scanner.nextLine();
            if (command.equals("add product")) {
                System.out.println("Enter productID: ");
                String id = scanner.nextLine();
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
                System.out.println("Enter special feature: ");
                String feature = scanner.nextLine();
                Product product = SellerAbilitiesManager.addProduct(id, name, companyName, price, category, seller, explanation, feature);
                new AddProductRequest(seller, RegisterAndLoginMenu.getCurrentManager(), product, category);
                System.out.println("request for adding product sent to manager");
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("add product");
                System.out.println("back");
            }
        }
    }

    //check
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
                    System.out.println(e.getMessage());
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

    //check
    public void showCategories() {
        System.out.println(SellerAbilitiesManager.showCategories());
    }

    //check
    public void viewOffs() {
        String command;
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        System.out.println(SellerAbilitiesManager.viewOffs(seller));
        while (true) {
            command = scanner.nextLine();
            Pattern viewOffPattern = Pattern.compile("view\\s(.+)");
            Matcher viewOffMatcher = viewOffPattern.matcher(command);
            Pattern editOffPattern = Pattern.compile("edit\\s(.+)");
            Matcher editOffMatcher = editOffPattern.matcher(command);
            if (command.matches("view\\s(.+)")) {
                viewOffMatcher.find();
                try {
                    SellerAbilitiesManager.isThereOffByThisId(seller, viewOffMatcher.group(1));
                    System.out.println(SellerAbilitiesManager.viewOffByGettingId(seller, viewOffMatcher.group(1)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("edit\\s(.+)")) {
                editOffMatcher.find();
                try {
                    SellerAbilitiesManager.isThereOffByThisId(seller, editOffMatcher.group(1));
                    System.out.println("enter the field you want to change: ");
                    String field = scanner.nextLine();
                    System.out.println("enter the content for this field: ");
                    String newContent = scanner.nextLine();
                    SellerAbilitiesManager.sendEditingOffRequest(editOffMatcher.group(1), seller, field, newContent);
                    System.out.println("request sent to manager");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("add off")) {
                ArrayList<String> productsName = new ArrayList<>();
                System.out.println("enter discount's id");
                String id = scanner.nextLine();
                System.out.println("enter discount's beginningDate(yyyy-mm-dd)");
                String beginningDate = scanner.nextLine();
                System.out.println("enter discount's endingDate(yyyy-mm-dd)");
                String endingDate = scanner.nextLine();
                System.out.println("enter discount's percent");
                String discountPercent = scanner.nextLine();
                System.out.println("how many products have this discount:");
                int num = Integer.valueOf(scanner.nextLine());
                for (int i = 0; i < num; i++) {
                    System.out.println("enter products name: ");
                    productsName.add(scanner.nextLine());
                }
                Discount discount = SellerAbilitiesManager.addDiscount(id, beginningDate, endingDate,
                        Double.parseDouble(discountPercent), productsName);
                new AddOffRequest(seller, RegisterAndLoginMenu.getCurrentManager(), discount);
                System.out.println("request sent to manager");
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("view [off id]");
                System.out.println("edit [off id]");
                System.out.println("add off");
                System.out.println("back");
            }
        }
    }

    //check
    public void viewBalance() {
        Seller seller = RegisterAndLoginMenu.getCurrentSeller();
        System.out.println(SellerAbilitiesManager.viewBalance(seller));
    }

}