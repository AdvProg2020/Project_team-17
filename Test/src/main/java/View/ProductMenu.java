package View;

import Controller.ProductsManager;
import Models.Product;

public class ProductMenu extends Menu {
    private ProductsManager productsManager;

    public ProductMenu(Menu parentMenu) {
        super("Product Menu", parentMenu);

    }
    @Override
    public void show() {
        System.out.println("1.digest");
        System.out.println("2.attributes");
        System.out.println("3.compare");
        System.out.println("4.comments");
        System.out.println("5.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            digest();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            attributes();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 3) {
            compare();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 4) {
            comments();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 5) {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void digest() {
        //Product product = Product.getProductWithId()
    }

    public void attributes() {

    }

    public void compare() {

    }

    public void comments() {

    }
}
