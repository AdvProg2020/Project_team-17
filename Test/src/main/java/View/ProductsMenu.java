package View;

import Controller.ProductsManager;

public class ProductsMenu extends Menu {
    ProductMenu productMenu = new ProductMenu(this);
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
        System.out.println("5.show product");
        System.out.println("6.back");
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
        } else if (input == 5) {
            showProduct();
            productMenu.show();
            productMenu.execute();
        } else if (input == 6) {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void products() {

    }

    public void viewCategories() {

    }

    public void filtering() {

    }

    public void sorting() {

    }

    public void showProducts() {

    }

    public void showProduct() {
        System.out.println("show product [productId]");
    }
}
