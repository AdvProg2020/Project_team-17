package View;

import Client.ClientController.CCartController;
import Controller.CartManager;
import Models.Product;
import View.PurchasingProcessMenus.ReceivingInformationPage;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CartMenu extends Menu {

    public CartMenu(Menu parentMenu) {
        super("Cart", parentMenu);
    }

    @Override
    public void show() {
        setCartScene();
    }

    public void setCartScene() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(25, 25, 25, 25));
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), " +
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#cdded5 0%, #f6f6f6 50%);" +
                " -fx-background-radius: 8,7,6; " +
                "-fx-background-insets: 0,1,2; " +
                "-fx-text-fill: #3193ff;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); " +
                "-fx-font-size: 1.2em; " +
                "-fx-padding: 4px;";
        Button backButton = new Button("Back");
        backButton.setStyle(style);
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
        HBox bar = new HBox(30);
        bar.getChildren().addAll(backButton, mainButtons);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                parentMenu.show();
            }
        });
        ArrayList<HBox> hBoxes = new ArrayList<>();
        hBoxes.add(bar);
        for (Product product : CartManager.cartProducts(RegisterCustomerMenu.getCurrentCustomer())) {
            HBox hBox = new HBox(5);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception e) {
            }
            ImageView imageView = new ImageView(image);
            Label name = new Label(product.getName());
            int numberOfProduct = CartManager.numberOfProducts(RegisterCustomerMenu.getCurrentCustomer(), product);
            Text text = new Text("Number: " + numberOfProduct + " Price per one: " + product.getPrice() + " Total price: "
                    + product.getPrice() * numberOfProduct);
            //text.setFont(Font.loadFont("file:src/main/java/Fonts/FiraSans-Medium.otf", 22));
            Image image1 = null;
            try {
                FileInputStream inputStream1 = new FileInputStream("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Images\\Plus.png");
                image1 = new Image(inputStream1);
            } catch (Exception e) {
            }
            ImageView imageView1 = new ImageView(image1);
            Button increase = new Button("", imageView1);
            increase.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.play();
                    try {
                        CCartController.increaseProduct(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    CartManager.increaseProduct(RegisterCustomerMenu.getCurrentCustomer(), product);
                }
            });
            Image image2 = null;
            try {
                FileInputStream inputStream2 = new FileInputStream("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Images\\Minus.png");
                image2 = new Image(inputStream2);
            } catch (Exception e) {
            }
            ImageView imageView2 = new ImageView(image2);
            Button decrease = new Button("", imageView2);
            decrease.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.play();
                    try {
                        CCartController.decreaseProduct(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    CartManager.decreaseProduct(RegisterCustomerMenu.getCurrentCustomer(), product);
                }
            });
            hBox.getChildren().addAll(imageView, name, text, increase, decrease);
            hBoxes.add(hBox);
        }
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(hBoxes);
//        Text totalCartPrice = new Text("Total price: " + CartManager.showTotalPriceOfCart(RegisterCustomerMenu.getCurrentCustomer()));
        Text totalCartPrice = new Text("Total price: " + CCartController.showTotalPrice());
        //totalCartPrice.setFont(Font.loadFont("file:src/main/java/Fonts/FiraSans-Medium.otf", 28));
        Button purchase = new Button("Purchase");
        purchase.setStyle(style);
        purchase.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.play();
                handleReceivingInformationPage();
            }
        });
        VBox vBox1 = new VBox(5);
        vBox1.getChildren().addAll(vBox, totalCartPrice, purchase);
        scrollPane.setContent(vBox1);
        Scene scene = new Scene(scrollPane, 1270, 650);
        Menu.window.setScene(scene);
    }

    private void handleReceivingInformationPage() {
        ReceivingInformationPage receivingInformationPage = new ReceivingInformationPage(this);
        receivingInformationPage.show();
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

    public void handleLogout() {
        if (RegisterCustomerMenu.getCurrentCustomer() != null) {
            RegisterCustomerMenu.removeFromOnlineCustomer(RegisterCustomerMenu.getCurrentCustomer());
            RegisterCustomerMenu.setCurrentCustomer(null);
        } else if (RegisterSellerMenu.getCurrentSeller() != null) {
            RegisterSellerMenu.removeFromOnlineSeller(RegisterSellerMenu.getCurrentSeller());
            RegisterSellerMenu.setCurrentSeller(null);
        } else if (RegisterManagerMenu.getCurrentManager() != null) {
            RegisterManagerMenu.removeFromOnlineManager(RegisterManagerMenu.getCurrentManager());
            RegisterManagerMenu.setCurrentManager(null);
        } else if (RegisterSupporterMenu.getCurrentSupporter() != null) {
            RegisterSupporterMenu.removeFromOnlineSupporter(RegisterSupporterMenu.getCurrentSupporter());
            RegisterSupporterMenu.setCurrentSupporter(null);
        }
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}