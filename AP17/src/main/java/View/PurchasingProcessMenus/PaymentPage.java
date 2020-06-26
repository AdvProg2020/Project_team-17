package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Controller.CartManager;
import View.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Paths;

public class PaymentPage extends Menu {
    public PaymentPage(Menu parentMenu) {
        super("Payment", parentMenu);
    }

    @Override
    public void show() {
        setPaymentScene();
    }

    public void setPaymentScene() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\Test\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(25, 25, 25, 25));
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), " +
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#cdded5 0%, #f6f6f6 50%);" +
                " -fx-background-radius: 8,7,6; " +
                "-fx-background-insets: 0,1,2; " +
                "-fx-text-fill: #3193ff;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); " +
                "-fx-font-size: 1.2em; " +
                "-fx-padding: 4px;";
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        accountsButton.setStyle(style);
        productButton.setStyle(style);
        discountButton.setStyle(style);
        logoutButton.setStyle(style);
        addActionForMainButtons(mediaPlayer, accountsButton, productButton, discountButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productButton, discountButton, logoutButton);
        pane.setTop(mainButtons);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        back.setStyle(style);
        Text title = new Text("YOUR BILL , if you are sure click done");
        title.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR, 32));
        VBox vBox1 = new VBox(10);
        Text totalPrice;
        if (DiscountCodePage.getCodeOfDiscountCode() != null) {
            totalPrice = new Text("total price with your discount code is going to be:" + "\n" +
                    CartManager.showTotalPriceOfCartIncludingDiscountCode(RegisterCustomerMenu.getCurrentCustomer(), DiscountCodePage.getDiscountCode()));
        } else {
            totalPrice = new Text("total price is going to be:" + "\n" +
                    CartManager.showTotalPriceOfCart(RegisterCustomerMenu.getCurrentCustomer()));
            totalPrice.setFont(Font.loadFont("file:src/main/java/Fonts/Titillium-Semibold.otf",28));
        }

        Button done = new Button("Done");
        done.setStyle(style);
        Label notify = new Label();
        done.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.play();
                try {
                    notify.setText(CustomerAbilitiesManager.finalPay(RegisterCustomerMenu.getCurrentCustomer(), DiscountCodePage.getDiscountCode()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.play();
            }
        });
        vBox.getChildren().addAll(back);
        pane.setLeft(vBox);
        vBox1.getChildren().addAll(title, totalPrice, done, notify);
        pane.setCenter(vBox1);
        Scene scene = new Scene(pane, 500, 500);
        Menu.window.setScene(scene);

    }

    public void addActionForMainButtons(MediaPlayer mediaPlayer ,Button accountsButton, Button productsButton, Button discountButton, Button logoutButton) {
        accountsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                handleAccountsMenu();
            }
        });
        productsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                handleProductsMenu();
            }
        });
        discountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                handleDiscountsMenu();
            }
        });
        logoutButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
              mediaPlayer.play();
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

}