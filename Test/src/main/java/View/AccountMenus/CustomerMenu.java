package View.AccountMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.Accounts.Customer;
import Models.Product;
import View.CartMenu;
import View.Menu;
import View.RegisterCustomerMenu;
import View.PurchasingProcessMenus.PurchaseMenu;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    public void show() {
        setPersonalInfoScene();
    }

    public void setPersonalInfoScene() {
        BorderPane pane = new BorderPane();
        Button backButton = new Button("Back");
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        mainButtons.getChildren().addAll(accountsButton, productButton, discountButton, logoutButton);
        pane.setTop(mainButtons);

        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
        VBox vBox = new VBox(10);
        Button viewOrdersButton = new Button("View orders");
        Button viewListOfDiscountCodes = new Button("Discount codes");
        Button editButton = new Button("Edit field");
        editButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEditScene();
            }
        });
        vBox.getChildren().addAll(backButton, viewOrdersButton, viewListOfDiscountCodes, editButton);
        pane.setLeft(vBox);
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        Label username = new Label("username: " + RegisterCustomerMenu.getCurrentCustomer().getUserName());
        Label firstName = new Label("first name: " + RegisterCustomerMenu.getCurrentCustomer().getFirstName());
        Label lastName = new Label("last name: " + RegisterCustomerMenu.getCurrentCustomer().getLastName());
        Label email = new Label("email: " + RegisterCustomerMenu.getCurrentCustomer().getEmail());
        Label phoneNumber = new Label("phone number: " + RegisterCustomerMenu.getCurrentCustomer().getPhoneNumber());
        Label credit = new Label("credit: " + RegisterCustomerMenu.getCurrentCustomer().getCredit());
        vBox1.getChildren().addAll(username, firstName, lastName, email, phoneNumber, credit);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setEditScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(5);
        Label notify = new Label();
        Button backButton = new Button("Back");
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
        Label title = new Label("Editing field");
        vBox.getChildren().addAll(backButton, title);
        pane.setTop(vBox);
        HBox hBox = new HBox(10);
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll("first name", "last name", "password", "email", "phone number");
        TextField newContent = new TextField();
        newContent.setPromptText("new content for this field");
        Button button = new Button("change");
        hBox.getChildren().addAll(field, newContent, button);
        VBox vBox1 = new VBox(5);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CustomerAbilitiesManager.changeField(RegisterCustomerMenu.getCurrentCustomer(), field.getValue(), newContent.getText());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("successfully changed");
            }
        });
        vBox1.getChildren().addAll(hBox,notify);
        pane.setCenter(vBox1);
        Scene scene = new Scene(pane, 400, 200);
        Menu.window.setScene(scene);
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
    public void viewDiscountCodes() {
        Customer customer = RegisterCustomerMenu.getCurrentCustomer();
        try {
            System.out.println(CustomerAbilitiesManager.showDiscountCodes(customer));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}