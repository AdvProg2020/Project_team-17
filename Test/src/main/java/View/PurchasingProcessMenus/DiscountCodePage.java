package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.DiscountCode;
import View.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DiscountCodePage extends Menu {
    private static String discountCode = "";

    public DiscountCodePage(Menu parentMenu) {
        super("Discount code", parentMenu);
    }

    @Override
    public void show() {
        setDiscountCodeScene();
    }

    public void setDiscountCodeScene() {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(25, 25, 25, 25));
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
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                parentMenu.show();
            }
        });
        pane.setLeft(back);
        Text title = new Text("discount code");
        title.setFont(Font.loadFont("file:src/main/java/Fonts/gangstergrotesk-bold.otf", 28));
        VBox vBox1 = new VBox(10);
        Label notify = new Label();
        TextField textField = new TextField();
        textField.setPromptText("discount code");
        Button next = new Button("done/next");
        Button nextWO = new Button("no discount code/next");

        next.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    CustomerAbilitiesManager.checkDiscountCodeValidation(textField.getText());
                    discountCode = textField.getText();
                    DiscountCode.getDiscountCodeWithCode(textField.getText()).setUsageOfDiscountCode(RegisterCustomerMenu.getCurrentCustomer());
                    handlePaymentPage();
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                }
            }
        });
        nextWO.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handlePaymentPage();
            }
        });
        vBox1.getChildren().addAll(title, textField, next, nextWO, notify);
        pane.setCenter(vBox1);
        Scene scene = new Scene(pane, 500, 500);
        Menu.window.setScene(scene);
    }

    public void handlePaymentPage() {
        PaymentPage paymentPage = new PaymentPage(this);
        paymentPage.show();
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

    public void handleAccountsMenu() {
        AccountsMenu accountsMenu = new AccountsMenu(this);
        accountsMenu.show();
    }

    public void handleProductsMenu() {
        ProductsMenu productsMenu = new ProductsMenu(this);
        productsMenu.show();
    }

    public void handleDiscountsMenu() {
        DiscountsMenu discountsMenu = new DiscountsMenu(this);
        discountsMenu.show();
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

    public static String getCodeOfDiscountCode() {
        return discountCode;
    }

    public static DiscountCode getDiscountCode() {
        return DiscountCode.getDiscountCodeWithCode(getCodeOfDiscountCode());
    }
}