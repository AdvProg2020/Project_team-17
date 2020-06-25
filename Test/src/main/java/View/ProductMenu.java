package View;

import Controller.ProductManager;
import Models.Product;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;

public class ProductMenu extends Menu {
    private Product product;

    public ProductMenu(Menu parentMenu, Product product) {
        super("Product Menu", parentMenu);
        this.product = product;
        product.addToVisitedTime();
    }

    @Override
    public void show() {
        setProductScene();
    }

    public void setProductScene() {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(25, 25, 25, 25));
        Button backButton = new Button("Back");
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Image image1 = null;
        try {
            FileInputStream inputStream1 = new FileInputStream("C:\\Users\\UX434FL\\IdeaProjects\\AP17\\Test\\src\\main\\java\\Images\\Cart.png");
            image1 = new Image(inputStream1);
        } catch (Exception e) {
        }
        ImageView imageView1 = new ImageView(image1);
        Button addToCart = new Button("", imageView1);
        Button accountsButton = new Button("Accounts");
        Button productButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        addActionForMainButtons(accountsButton, productButton, discountButton, logoutButton, addToCart);
        mainButtons.getChildren().addAll(addToCart, accountsButton, productButton, discountButton, logoutButton);
        pane.setTop(mainButtons);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
        pane.setLeft(backButton);
        Image image = null;
        try {
            FileInputStream inputStream = new FileInputStream(product.getPath());
            image = new Image(inputStream);
        } catch (Exception e) {
        }
        ImageView imageView = new ImageView(image);
        //TODO zoom
        Label name = new Label(product.getName());
        Label companyName = new Label(product.getCompanyName());
        Label category = new Label(product.getCategory().getCategoryName());
        Label price = new Label(product.getPrice() + "$");
        Label explanation = new Label(product.getExplanation());
        Label feature = new Label(product.getProductsSpecialFeature());
        Label score = new Label(Double.toString(product.getAverageScore()));
        VBox info = new VBox(5);
        info.getChildren().addAll(name, companyName, category, price, explanation, feature, score);
        HBox hBox = new HBox(10);
        VBox vBox = new VBox(5);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(ProductManager.showComments(product));
        TextField comment = new TextField();
        comment.setPromptText("comment");
        Button submitComment = new Button("submit");
        HBox hBox1 = new HBox(5);
        hBox1.getChildren().addAll(comment, submitComment);
        submitComment.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ProductManager.addComment(RegisterCustomerMenu.getCurrentCustomer(), product, comment.getText());
                comment.clear();
            }
        });
        if (RegisterCustomerMenu.getCurrentCustomer() != null) {
            if (ProductManager.doesCustomerBoughtThisProduct(RegisterCustomerMenu.getCurrentCustomer(), product)) {
                TextField rate = new TextField();
                rate.setPromptText("product score");
                Button submitScore = new Button("Submit");
                submitScore.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ProductManager.rateProduct(RegisterCustomerMenu.getCurrentCustomer(), product, Double.parseDouble(rate.getText()));
                        rate.clear();
                    }
                });
                HBox hBox2 = new HBox(5);
                hBox2.getChildren().addAll(rate, submitComment);
                vBox.getChildren().addAll(listView, hBox1, hBox2);
            } else {
                vBox.getChildren().addAll(listView, hBox1);
            }
        } else {
            vBox.getChildren().addAll(listView, hBox1);
        }
        hBox.getChildren().addAll(imageView, info, vBox);
        pane.setCenter(hBox);
        Scene scene = new Scene(pane, 700, 630);
        Menu.window.setScene(scene);
    }

    public void addActionForMainButtons(Button accountsButton, Button productsButton, Button discountButton, Button logoutButton, Button cartButton) {
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
        cartButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleAddCart();
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

    public void handleAddCart() {
        ProductManager.addToCart(RegisterCustomerMenu.getCurrentCustomer(), product);
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


    //check
    public void compare() {
        System.out.println("enter product id you want to compare with this product: ");
        String id = scanner.nextLine();
        System.out.println(ProductManager.compareProduct(product, id));
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}