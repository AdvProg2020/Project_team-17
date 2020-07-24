package View;

import Client.ClientController.CProductController;
import Controller.ProductManager;
import Models.Product;
import View.RegisterMenus.RegisterCustomerMenu;
import View.RegisterMenus.RegisterManagerMenu;
import View.RegisterMenus.RegisterSellerMenu;
import View.RegisterMenus.RegisterSupporterMenu;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;

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
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        BorderPane pane = new BorderPane();
        Label notify = new Label();
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
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        HBox mainButtons = new HBox(5);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Image image1 = null;
        try {
            FileInputStream inputStream1 = new FileInputStream("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Images\\Cart.png");
            image1 = new Image(inputStream1);
        } catch (Exception ignored) {
        }
        ImageView imageView1 = new ImageView(image1);
        Button addToCart = new Button("", imageView1);
        Button accountsButton = new Button("Accounts");
        accountsButton.setStyle(style);
        Button productButton = new Button("Products");
        productButton.setStyle(style);
        Button discountButton = new Button("Discounts");
        discountButton.setStyle(style);
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle(style);
        addToCart.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (RegisterCustomerMenu.getCurrentCustomer() != null) {
                    handleAddCart();
                } else {
                    notify.setText("customer should be login");
                    notify.setStyle("-fx-text-fill: #ff4f59");
                }
            }
        });
        VBox vBox1 = new VBox(10);
        vBox1.getChildren().addAll(addToCart, notify);
        addActionForMainButtons(mediaPlayer, accountsButton, productButton, discountButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productButton, discountButton, logoutButton, vBox1);
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
        } catch (Exception ignored) {
        }

        final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
        ImageView imageView = new ImageView(image);
        imageView.preserveRatioProperty().set(true);
        zoomProperty.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
                imageView.setFitWidth(zoomProperty.get() * 4);
                imageView.setFitHeight(zoomProperty.get() * 3);

            }
        });
        pane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() > 0) {
                    zoomProperty.set(zoomProperty.get() * 1.1);
                } else if (event.getDeltaY() < 0) {
                    zoomProperty.set(zoomProperty.get() / 1.1);
                }
            }
        });

        Text name = new Text("Product name: " + product.getName());
        Text companyName = new Text("Company name: " + product.getCompanyName());
        Text category = new Text("Category: " + product.getCategory().getCategoryName());
        Text price = new Text("Product price: " + product.getPrice() + "$");
        Text explanation = new Text("Explanation: " + product.getExplanation());
        Text feature = new Text("Feature: " + product.getProductsSpecialFeature());
        Text score = new Text("Product Score: " + product.getAverageScore());
        Text seller = new Text("Seller: " + product.getSeller());
        TextField textField = new TextField();
        textField.setPromptText("enter the path");
        Button button = new Button("show video");

        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setVideoScene(textField.getText());
            }
        });

        VBox info = new VBox(5);
        info.getChildren().addAll(name, companyName, category, price, explanation, feature, score, seller, textField, button);
        HBox hBox = new HBox(10);
        VBox vBox = new VBox(5);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(ProductManager.showComments(product));
        TextField comment = new TextField();
        comment.setPromptText("comment");
        comment.setStyle(style);
        Button submitComment = new Button("submit");
        submitComment.setStyle(style);
        HBox hBox1 = new HBox(5);
        hBox1.getChildren().addAll(comment, submitComment);
        HBox hBox3 = new HBox(5);
        TextField field = new TextField();
        field.setPromptText("compare product with ...");
        field.setStyle(style);
        Button compare = new Button("compare");
        compare.setStyle(style);
        Label text = new Label();
        compare.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                try {
                    setCompareProductScene(CProductController.compareProduct(field.getText()));
                } catch (Exception e) {
                    text.setText(e.getMessage());
                    text.setStyle("-fx-text-fill: #ff4f59");
                }
//                if (Product.getProductByName(field.getText()) != null) {
//                    setCompareProductScene(Product.getProductByName(field.getText()));
//                } else {
//                    text.setText("there isn't any product with this ID");
//                    text.setStyle("-fx-text-fill: #ff4f59");
//                }
//            }
            }
        });
        hBox3.getChildren().addAll(field, compare);
        submitComment.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.play();
                try {
//                    ProductManager.addComment(RegisterCustomerMenu.getCurrentCustomer(), product, comment.getText());
                    CProductController.commentProduct(product.getProductId(), comment.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (RegisterCustomerMenu.getCurrentCustomer() != null) {
            if (ProductManager.doesCustomerBoughtThisProduct(RegisterCustomerMenu.getCurrentCustomer(), product)) {
                TextField rate = new TextField();
                rate.setPromptText("product score");
                rate.setStyle(style);
                Button submitScore = new Button("Submit");
                submitScore.setStyle(style);
                submitScore.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        mediaPlayer.play();
                        try {
                            CProductController.rateProduct(product.getProductId(), Double.parseDouble(rate.getText()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        ProductManager.rateProduct(RegisterCustomerMenu.getCurrentCustomer(), product, Double.parseDouble(rate.getText()));
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
        hBox.getChildren().addAll(imageView, info, vBox, hBox3);
        pane.setCenter(hBox);
        Scene scene = new Scene(pane, 700, 630);
        Menu.window.setScene(scene);
    }

    private void setCompareProductScene(Product product1) {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        BorderPane pane = new BorderPane();
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

        Button accountsButton = new Button("Accounts");
        accountsButton.setStyle(style);
        Button productButton = new Button("Products");
        productButton.setStyle(style);
        Button discountButton = new Button("Discounts");
        discountButton.setStyle(style);
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle(style);

        addActionForMainButtons(mediaPlayer, accountsButton, productButton, discountButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productButton, discountButton, logoutButton);
        pane.setTop(mainButtons);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                show();
            }
        });
        pane.setLeft(backButton);

        Image image = null;
        try {
            FileInputStream inputStream = new FileInputStream(product.getPath());
            image = new Image(inputStream);
        } catch (Exception ignored) {
        }

        final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
        ImageView imageView = new ImageView(image);
        imageView.preserveRatioProperty().set(true);
        zoomProperty.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
                imageView.setFitWidth(zoomProperty.get() * 4);
                imageView.setFitHeight(zoomProperty.get() * 3);

            }
        });
        pane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() > 0) {
                    zoomProperty.set(zoomProperty.get() * 1.1);
                } else if (event.getDeltaY() < 0) {
                    zoomProperty.set(zoomProperty.get() / 1.1);
                }
            }
        });

        Text name = new Text("Product name: " + product.getName());
        Text companyName = new Text("Company name: " + product.getCompanyName());
        Text category = new Text("Category: " + product.getCategory().getCategoryName());
        Text price = new Text("Product price: " + product.getPrice() + "$");
        Text explanation = new Text("Explanation: " + product.getExplanation());
        Text feature = new Text("Feature: " + product.getProductsSpecialFeature());
        Text score = new Text("Product Score: " + product.getAverageScore());
        Text seller = new Text("Seller: " + product.getSeller());

        VBox info = new VBox(5);
        info.getChildren().addAll(name, companyName, category, price, explanation, feature, score, seller);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(imageView, info);

        Image image1 = null;
        try {
            FileInputStream inputStream = new FileInputStream(product.getPath());
            image1 = new Image(inputStream);
        } catch (Exception e) {
        }

        final DoubleProperty zoomProperty1 = new SimpleDoubleProperty(200);
        ImageView imageView1 = new ImageView(image1);
        imageView1.preserveRatioProperty().set(true);
        zoomProperty1.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
                imageView1.setFitWidth(zoomProperty1.get() * 4);
                imageView1.setFitHeight(zoomProperty1.get() * 3);

            }
        });
        pane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() > 0) {
                    zoomProperty1.set(zoomProperty1.get() * 1.1);
                } else if (event.getDeltaY() < 0) {
                    zoomProperty1.set(zoomProperty1.get() / 1.1);
                }
            }
        });

        Text name1 = new Text("Product name: " + product1.getName());
        Text companyName1 = new Text("Company name: " + product1.getCompanyName());
        Text category1 = new Text("Category: " + product1.getCategory().getCategoryName());
        Text price1 = new Text("Product price: " + product1.getPrice() + "$");
        Text explanation1 = new Text("Explanation: " + product1.getExplanation());
        Text feature1 = new Text("Feature: " + product1.getProductsSpecialFeature());
        Text score1 = new Text("Product Score: " + product1.getAverageScore());
        Text seller1 = new Text("Seller: " + product1.getSeller());

        VBox info1 = new VBox(5);
        info1.getChildren().addAll(name1, companyName1, category1, price1, explanation1, feature1, score1, seller1);

        HBox hBox1 = new HBox(10);
        hBox1.getChildren().addAll(imageView1, info1);

        HBox hBox2 = new HBox(30);
        hBox2.getChildren().addAll(hBox, hBox1);
        pane.setCenter(hBox2);
        Scene scene = new Scene(pane, 1270, 650);
        Menu.window.setScene(scene);
    }

    public void setVideoScene(String path) {
        BorderPane pane = new BorderPane();
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), " +
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#cdded5 0%, #f6f6f6 50%);" +
                " -fx-background-radius: 8,7,6; " +
                "-fx-background-insets: 0,1,2; " +
                "-fx-text-fill: #3193ff;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); " +
                "-fx-font-size: 1.2em; " +
                "-fx-padding: 4px;";
        VBox vBox = new VBox(10);
        Label notify = new Label();
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                show();
                notify.setText("");
            }
        });
        Label title = new Label("video for product");
        vBox.getChildren().addAll(backButton, title);
        pane.setTop(vBox);

        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        pane.setCenter(mediaView);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
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

    public void handleAddCart() {
        ProductManager.addToCart(RegisterCustomerMenu.getCurrentCustomer(), product);
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

    public void setProduct(Product product) {
        this.product = product;
    }
}