package View.AccountMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.Accounts.Customer;
import Models.Product;
import View.CartMenu;
import View.Menu;
import View.RegisterCustomerMenu;
import View.PurchasingProcessMenus.PurchaseMenu;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
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
        design();

    }
    public void design(){
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
        VBox vBox = new VBox(10);
        Button backButton = new Button("Back");
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
        Button viewOrdersButton = new Button("View orders");
        Button viewListOfDiscountCodes = new Button("Discount codes");
        vBox.getChildren().addAll(backButton,viewOrdersButton,viewListOfDiscountCodes);
        VBox vBox1 = new VBox(10);
        Label username = new Label(RegisterCustomerMenu.getCurrentCustomer().getUserName());
        Label firstName = new Label(RegisterCustomerMenu.getCurrentCustomer().getFirstName());
        Label lastName = new Label(RegisterCustomerMenu.getCurrentCustomer().getLastName());
        Label email = new Label(RegisterCustomerMenu.getCurrentCustomer().getEmail());
        Label phoneNumber = new Label(RegisterCustomerMenu.getCurrentCustomer().getPhoneNumber());
        Label address = new Label(RegisterCustomerMenu.getCurrentCustomer().getAddress());
        Label credit = new Label(Double.toString(RegisterCustomerMenu.getCurrentCustomer().getCredit()));
        vBox1.getChildren().addAll(username,firstName,lastName,email,phoneNumber,address,credit);
        HBox hBox = new HBox(30);
        hBox.getChildren().addAll(vBox,vBox1);
        Scene scene = new Scene(pane, 600 ,600);
        Menu.window.setScene(scene);

    }
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