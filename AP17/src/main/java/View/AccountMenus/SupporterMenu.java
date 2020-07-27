package View.AccountMenus;

import Client.ClientController.AccountsController.CCustomerController;
import Client.ClientController.AccountsController.CManagerController;
import Client.ClientController.AccountsController.CSellerController;
import Client.ClientController.AccountsController.CSupporterController;
import Server.ServerController.AccountsController.CustomerController;
import Server.ServerController.AccountsController.ManagerController;
import Server.ServerController.AccountsController.SellerController;
import Server.ServerController.AccountsController.SupporterController;
import View.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import java.io.FileInputStream;
import java.nio.file.Paths;

public class SupporterMenu extends Menu {
    public SupporterMenu(Menu parentMenu) {
        super("Supporter Menu", parentMenu);
    }

    @Override
    public void show() {
        setPersonalInfoScene();
    }

    public void setPersonalInfoScene() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        BorderPane pane = new BorderPane();
        Button backButton = new Button("Back");
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), " +
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#cdded5 0%, #f6f6f6 50%);" +
                " -fx-background-radius: 8,7,6; " +
                "-fx-background-insets: 0,1,2; " +
                "-fx-text-fill: #3193ff;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); " +
                "-fx-font-size: 1.2em; " +
                "-fx-padding: 4px;";
        HBox mainButtons = new HBox(10);
        backButton.setStyle(style);
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
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(backButton);
        pane.setLeft(vBox);
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        Image image = null;
        try {
            //TODO BUG
            FileInputStream inputStream = new FileInputStream(CManagerController.getManager().getPath());
            image = new Image(inputStream);
        } catch (Exception e) {
        }
        ImageView imageView = new ImageView(image);
        Text title = new Text("Supporter");
        Text info = new Text();
        try {
            info = new Text(CSupporterController.showSupporterInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Text username = new Text("username: " + ManagerController.getManager().getUserName());
//        Text firstName = new Text("first name: " + ManagerController.getManager().getFirstName());
//        Text lastName = new Text("last name: " + ManagerController.getManager().getLastName());
//        Text email = new Text("email: " + ManagerController.getManager().getEmail());
//        Text phoneNumber = new Text("phone number: " + ManagerController.getManager().getPhoneNumber());
        title.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
//        username.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        firstName.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        lastName.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        email.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        phoneNumber.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        vBox1.getChildren().addAll(imageView, title, username, firstName, lastName, email, phoneNumber);
        vBox1.getChildren().addAll(imageView, title, info);

        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void addActionForMainButtons(MediaPlayer mediaPlayer, Button accountsButton, Button productsButton, Button discountButton, Button logoutButton) {
        accountsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
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
