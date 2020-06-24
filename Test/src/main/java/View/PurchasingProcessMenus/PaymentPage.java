package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Controller.CartManager;
import View.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PaymentPage extends Menu {
    public PaymentPage(Menu parentMenu) {
        super("Payment", parentMenu);
    }

    @Override
    public void show() {
        setPaymentScene();
    }

    public void setPaymentScene() {
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
        Label title = new Label("YOUR BILL , if you are sure click done");
        VBox vBox1 = new VBox(10);
        Label totalPrice;
        if (DiscountCodePage.getCodeOfDiscountCode() != null) {
            totalPrice = new Label("total price with your discount code is going to be:" + "\n" +
                    CartManager.showTotalPriceOfCartIncludingDiscountCode(RegisterCustomerMenu.getCurrentCustomer(), DiscountCodePage.getDiscountCode()));
        } else {
            totalPrice = new Label("total price is going to be:" + "\n" +
                    CartManager.showTotalPriceOfCart(RegisterCustomerMenu.getCurrentCustomer()));
        }

        Button done = new Button("Done");
        Label notify = new Label();
        done.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                notify.setText(CustomerAbilitiesManager.finalPay(RegisterCustomerMenu.getCurrentCustomer(), DiscountCodePage.getDiscountCode()));
            }
        });
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
        vBox.getChildren().addAll(back);
        pane.setLeft(vBox);
        vBox1.getChildren().addAll(title, totalPrice, done, notify);
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
                //TODO
            }
        });
        discountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        logoutButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleLogout();
            }
        });
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

}
