package View.PurchasingProcessMenus;

import Client.ClientController.AccountsController.CCustomerController;
import Client.ClientController.AccountsController.CManagerController;
import Client.ClientController.AccountsController.CSellerController;
import Client.ClientController.AccountsController.CSupporterController;
import Client.ClientController.CCartController;
import Controller.AccountsManager.CustomerAbilitiesManager;
import Controller.CartManager;
import Server.ServerController.AccountsController.CustomerController;
import Server.ServerController.AccountsController.ManagerController;
import Server.ServerController.AccountsController.SellerController;
import Server.ServerController.AccountsController.SupporterController;
import View.*;
import View.RegisterMenus.RegisterCustomerMenu;
import View.RegisterMenus.RegisterManagerMenu;
import View.RegisterMenus.RegisterSellerMenu;
import View.RegisterMenus.RegisterSupporterMenu;
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
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
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
//            totalPrice = new Text("total price with your discount code is going to be:" + "\n" +
//                    CartManager.showTotalPriceOfCartIncludingDiscountCode(RegisterCustomerMenu.getCurrentCustomer(), DiscountCodePage.getDiscountCode()));
            totalPrice = new Text("total price with your discount code is going to be:" + "\n" +
                    CCartController.showTotalPriceWithDiscountCode(DiscountCodePage.getDiscountCode().getDiscountCode()));
        } else {
//            totalPrice = new Text("total price is going to be:" + "\n" +
//                    CartManager.showTotalPriceOfCart(RegisterCustomerMenu.getCurrentCustomer()));
            totalPrice = new Text("total price is going to be:" + "\n" +
                    CCartController.showTotalPrice());
            totalPrice.setFont(Font.loadFont("file:src/main/java/Fonts/Titillium-Semibold.otf", 28));
        }

        Button done = new Button("Done");
        done.setStyle(style);
        Label notify = new Label();
        done.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.play();
                try {
                    CartManager.changeProductsState(RegisterCustomerMenu.getCurrentCustomer());
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

    public void addActionForMainButtons(MediaPlayer mediaPlayer, Button accountsButton, Button productsButton, Button discountButton, Button logoutButton) {
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

//    public void handleLogout() {
//        if (RegisterCustomerMenu.getCurrentCustomer() != null) {
//            RegisterCustomerMenu.removeFromOnlineCustomer(RegisterCustomerMenu.getCurrentCustomer());
//            RegisterCustomerMenu.setCurrentCustomer(null);
//        } else if (RegisterSellerMenu.getCurrentSeller() != null) {
//            RegisterSellerMenu.removeFromOnlineSeller(RegisterSellerMenu.getCurrentSeller());
//            RegisterSellerMenu.setCurrentSeller(null);
//        } else if (RegisterManagerMenu.getCurrentManager() != null) {
//            RegisterManagerMenu.removeFromOnlineManager(RegisterManagerMenu.getCurrentManager());
//            RegisterManagerMenu.setCurrentManager(null);
//        } else if (RegisterSupporterMenu.getCurrentSupporter() != null) {
//            RegisterSupporterMenu.removeFromOnlineSupporter(RegisterSupporterMenu.getCurrentSupporter());
//            RegisterSupporterMenu.setCurrentSupporter(null);
//        }
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.show();
//    }

    public void handleLogout() {
        if (CustomerController.getCustomer() != null) {
            CustomerController.setCustomer(null);
            CCustomerController.setCustomer(null);
            CustomerController.removeOnlineCustomer(CustomerController.getCustomer());
            CCustomerController.removeOnlineCustomer(CCustomerController.getCustomer());
        } else if (SellerController.getSeller() != null) {
            SellerController.setSeller(null);
            CSellerController.setSeller(null);
            SellerController.removeOnlineSeller(SellerController.getSeller());
            CSellerController.removeOnlineSeller(CSellerController.getSeller());
        } else if (ManagerController.getManager() != null) {
            ManagerController.setManager(null);
            CManagerController.setManager(null);
            ManagerController.removeOnlineManger(ManagerController.getManager());
            CManagerController.removeOnlineManger(CManagerController.getManager());
        } else if (SupporterController.getSupporter() != null) {
            SupporterController.setSupporter(null);
            CSupporterController.setSupporter(null);
            SupporterController.removeOnlineSupporter(SupporterController.getSupporter());
            CSupporterController.removeOnlineSupporter(CSupporterController.getSupporter());
        }
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }

}