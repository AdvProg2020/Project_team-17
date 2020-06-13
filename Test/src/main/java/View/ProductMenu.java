package View;

import Controller.ProductManager;
import Models.Accounts.Customer;
import Models.Product;

import java.io.FileNotFoundException;

public class ProductMenu extends Menu {
    //RegisterAndLoginMenu registerAndLoginMenu = new RegisterAndLoginMenu(this);
    Menu registerAndLoginMenu = Menu.getMenu("Register And Login ");
    private Product product;

    public ProductMenu(Menu parentMenu, Product product) {
        super("Product Menu", parentMenu);
        this.product = product;
        product.addToVisitedTime();
    }

    @Override
    public void show() {
        System.out.println("1.digest");
        System.out.println("2.attributes");
        System.out.println("3.compare");
        System.out.println("4.comments");
        System.out.println("5.back");
    }

    //check
    public void digest() throws FileNotFoundException {
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        String command;
        System.out.println(ProductManager.digestAttributes(product));
        while (true) {
            command = scanner.nextLine();
            if (command.equals("add to cart")) {
                if (customer == null) {
                    registerAndLoginMenu.show();
                    //registerAndLoginMenu.execute();
                } else {
                    ProductManager.addToCart(customer, product);
                    System.out.println("product successfully added to your cart");
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("add to cart");
                System.out.println("back");
            }
        }
    }

    //check
    public void attributes() {
        System.out.println(ProductManager.showAttributes(product));
    }

    //check
    public void compare() {
        //inja miad y id product digaro migire ba producti k alan to safashim moghayese mikonim?
        //age injori bashe k doroste age na kolan miad id dota ro migire moghayese mikonae bayad avaz konim
        System.out.println("enter product id you want to compare with this product: ");
        String id = scanner.nextLine();
        System.out.println(ProductManager.compareProduct(product, id));
    }

    //check
    public void comments() {
        String command;
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        System.out.println(ProductManager.showComments(product));
        while (true) {
            command = scanner.nextLine();
            if (command.equals("add comment")) {
                System.out.println("enter your comment");
                String comment = scanner.nextLine();
                ProductManager.addComment(customer, product, comment);
                System.out.println("comment added to product");
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("add comment");
                System.out.println("back");
            }
        }
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
