package View;

import Controller.DiscountManager;
import Controller.ProductsManager;
import Models.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsMenu extends Menu {
    private ProductsManager productsManager;

    public ProductsMenu(Menu parentMenu) {
        super("Products", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.view categories");
        System.out.println("2.filtering");
        System.out.println("3.sorting");
        System.out.println("4.show products");
        System.out.println("5.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
          if (input == 1) {
            viewCategories();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            filtering();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 3) {
            sorting();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 4) {
            showProducts();
            parentMenu.show();
            parentMenu.execute();
        }  else if (input == 5) {
            parentMenu.show();
            parentMenu.execute();
        }
    }


    public void viewCategories() {
        System.out.println(ProductsManager.showCategory());
    }

    public void filtering() {

    }

    public void sorting() {

    }

    public void showProducts() {
        String command;
        DiscountManager.showDiscountProducts();
        while (true){
            command = scanner.nextLine();
            Pattern showProductByIdPattern=Pattern.compile("show product\\s(.+)");
            Matcher showProductByIdMatcher= showProductByIdPattern.matcher(command);
            if(command.matches("show product\\\\s(.+)")){
                showProductByIdMatcher.find();
                Product product = Product.getProductWithId(showProductByIdMatcher.group(1));
                ProductMenu productMenu = new ProductMenu(this,product);
                productMenu.show();
                productMenu.execute();
            }else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("show product [productID]");
                System.out.println("back");
            }else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }

    }

}
