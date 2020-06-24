package View.PurchasingProcessMenus;

import View.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReceivingInformationPage extends Menu {
    private static String addressString;
    private static String phoneNumString;

    public ReceivingInformationPage(Menu parentMenu) {
        super("Receiver Information", parentMenu);
    }

    @Override
    public void show() {
        setReceivingInfoScene();
    }


    public void setReceivingInfoScene() {
        BorderPane pane = new BorderPane();
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        addActionForMainButtons(accountsButton, productButton, discountButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productButton, discountButton, logoutButton);
        pane.setTop(mainButtons);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        Label title = new Label("Receiving information to continue purchasing");
        VBox vBox1 = new VBox(10);
        TextField address = new TextField();
        address.setPromptText("address");

        TextField phoneNumber = new TextField();
        phoneNumber.setPromptText("phone number");

        Button next = new Button("next");
        next.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addressString = address.getText();
                phoneNumString = phoneNumber.getText();
                handleDiscountCodePage();
                address.clear();
                phoneNumber.clear();
            }
        });
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                parentMenu.show();
                address.clear();
                phoneNumber.clear();
            }
        });
        vBox.getChildren().addAll(back);
        pane.setLeft(vBox);
        vBox1.getChildren().addAll(title, address, phoneNumber, next);
        pane.setCenter(vBox1);
        Scene scene = new Scene(pane, 400, 400);
        Menu.window.setScene(scene);
    }

    public void addActionForMainButtons(Button accountsButton, Button productsButton, Button discountButton, Button logoutButton) {
        accountsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleAccountsMenu();
            }
        });
        productsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleProductsMenu();
            }
        });
        discountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleDiscountsMenu();
            }
        });
        logoutButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleLogout();
            }
        });
    }

    public void handleProductsMenu() {
        ProductsMenu productsMenu = new ProductsMenu(this);
        productsMenu.show();
    }

    public void handleDiscountsMenu() {
        DiscountsMenu discountsMenu = new DiscountsMenu(this);
        discountsMenu.show();
    }

    public void handleAccountsMenu() {
        AccountsMenu accountsMenu = new AccountsMenu(this);
        accountsMenu.show();
    }

    public void handleLogout() {
        if (RegisterCustomerMenu.getCurrentCustomer() != null) {
            RegisterCustomerMenu.setCurrentCustomer(null);
        } else if (RegisterSellerMenu.getCurrentSeller() != null) {
            RegisterSellerMenu.setCurrentSeller(null);
        } else if (RegisterManagerMenu.getCurrentManager() != null) {
            RegisterManagerMenu.setCurrentManager(null);
        }
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }

    public void handleDiscountCodePage() {
        DiscountCodePage discountCodePage = new DiscountCodePage(this);
        discountCodePage.show();
    }

    public static String getAddress() {
        return addressString;
    }

    public static String getPhoneNum() {
        return phoneNumString;
    }
}