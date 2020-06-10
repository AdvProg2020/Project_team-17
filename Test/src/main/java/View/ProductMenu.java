package View;

import Controller.ProductManager;
import Models.Accounts.Customer;
import Models.Product;

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

    //check
    public void digest() {
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
        String command;
        System.out.println(ProductManager.digestAttributes(product));
        while (true) {
            command = scanner.nextLine();
            if (command.equals("add to cart")) {
                if (customer == null) {
                    registerAndLoginMenu.show();
                    registerAndLoginMenu.execute();
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
        Customer customer = RegisterAndLoginMenu.getCurrentCustomer();
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
