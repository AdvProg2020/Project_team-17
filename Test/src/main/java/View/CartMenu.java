package View;

import Controller.CartManager;
import Models.Product;
import View.PurchasingProcessMenus.ReceivingInformationPage;
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

import java.io.FileInputStream;
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
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(25, 25, 25, 25));
        Button backButton = new Button("Back");
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        addActionForMainButtons(accountsButton, productButton, discountButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productButton, discountButton, logoutButton);
        HBox bar = new HBox(30);
        //TODO check spacing
        bar.getChildren().addAll(backButton, mainButtons);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
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
            Label number = new Label(Integer.toString(numberOfProduct));
            Label price = new Label("price per one: " + product.getPrice() + " total price: " + product.getPrice() * numberOfProduct);
            Image image1 = null;
            try {
                FileInputStream inputStream1 = new FileInputStream("C:\\Users\\UX434FL\\IdeaProjects\\AP17\\Test\\src\\main\\java\\Images\\Plus.png");
                image1 = new Image(inputStream1);
            } catch (Exception e) {
            }
            ImageView imageView1 = new ImageView(image1);
            Button increase = new Button("", imageView1);
            increase.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CartManager.increaseProduct(RegisterCustomerMenu.getCurrentCustomer(), product);
                }
            });
            Image image2 = null;
            try {
                FileInputStream inputStream2 = new FileInputStream("C:\\Users\\UX434FL\\IdeaProjects\\AP17\\Test\\src\\main\\java\\Images\\Minus.png");
                image2 = new Image(inputStream2);
            } catch (Exception e) {
            }
            ImageView imageView2 = new ImageView(image2);
            Button decrease = new Button("", imageView2);
            decrease.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CartManager.decreaseProduct(RegisterCustomerMenu.getCurrentCustomer(), product);
                }
            });
            hBox.getChildren().addAll(imageView, name, number, price, increase, decrease);
            hBoxes.add(hBox);
        }
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(hBoxes);
        Label totalCartPrice = new Label("total price: " + CartManager.showTotalPriceOfCart(RegisterCustomerMenu.getCurrentCustomer()));
        Button purchase = new Button("Purchase");
        purchase.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleReceivingInformationPage();
            }
        });
        VBox vBox1 = new VBox(5);
        vBox1.getChildren().addAll(vBox, totalCartPrice, purchase);
        scrollPane.setContent(vBox1);
        Scene scene = new Scene(scrollPane, 600, 600);
        Menu.window.setScene(scene);
    }

    private void handleReceivingInformationPage() {
        ReceivingInformationPage receivingInformationPage = new ReceivingInformationPage(this);
        receivingInformationPage.show();
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
}