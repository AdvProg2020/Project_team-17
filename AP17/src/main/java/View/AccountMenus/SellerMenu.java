package View.AccountMenus;

import Client.ClientController.AccountsController.CCustomerController;
import Client.ClientController.AccountsController.CManagerController;
import Client.ClientController.AccountsController.CSellerController;
import Client.ClientController.AccountsController.CSupporterController;
import Client.ClientController.CWalletController;
import Controller.AccountsManager.SellerAbilitiesManager;
import Models.Category;
import Models.Discount;
import Models.Product;
import Server.ServerController.AccountsController.CustomerController;
import Server.ServerController.AccountsController.ManagerController;
import Server.ServerController.AccountsController.SellerController;
import Server.ServerController.AccountsController.SupporterController;
import View.*;
import View.Menu;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class SellerMenu extends Menu {
    Product selectedProduct;
    Discount selectedDiscount;

    public SellerMenu(Menu parentMenu) {
        super("Seller ", parentMenu);
    }

    @Override
    public void show() {
        setPersonalScene();
    }

    public void setPersonalScene() {
        //String path = "C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Sounds\\button.mp3";
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
        HBox mainButtons = new HBox(10);
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
                mediaPlayer.play();
                parentMenu.show();
            }
        });

        VBox vBox = new VBox(10);
        Button viewSalesHistory = new Button("View sales history");
        viewSalesHistory.setStyle(style);
        viewSalesHistory.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setSalesHistoryScene();
            }
        });
        Button manageProductButton = new Button("Manage products");
        manageProductButton.setStyle(style);
        manageProductButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setManageProductScene();
            }
        });
        Button viewOffsButton = new Button("View offs");
        viewOffsButton.setStyle(style);
        viewOffsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setOffsScene();
            }
        });

        Button wallet = new Button("Wallet");
        wallet.setStyle(style);
        wallet.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setWalletScene();
            }
        });

        Button showCategories = new Button("Show category");
        showCategories.setStyle(style);
        showCategories.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setShowCategoriesScene();
            }
        });

        Button editButton = new Button("Edit field");
        editButton.setStyle(style);
        editButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setEditScene();
            }
        });

        Button addAuctionButton = new Button("Add auction");
        addAuctionButton.setStyle(style);
        addAuctionButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setAddAuctionScene();
            }
        });
        vBox.getChildren().addAll(backButton, viewSalesHistory, manageProductButton, showCategories, viewOffsButton, editButton, addAuctionButton, wallet);
        pane.setLeft(vBox);

        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        Text title = new Text("SELLER");
        Image image = null;
        try {
            FileInputStream inputStream = new FileInputStream(CSellerController.getSeller().getPath());
            image = new Image(inputStream);
        } catch (Exception e) {
        }
        Text info = new Text();
        try {
            info = new Text(CSellerController.showSellerInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
//        Text username = new Text("username: " + RegisterSellerMenu.getCurrentSeller().getUserName());
//        Text firstName = new Text("first name: " + RegisterSellerMenu.getCurrentSeller().getFirstName());
//        Text lastName = new Text("last name: " + RegisterSellerMenu.getCurrentSeller().getLastName());
//        Text email = new Text("email: " + RegisterSellerMenu.getCurrentSeller().getEmail());
//        Text phoneNumber = new Text("phone number: " + RegisterSellerMenu.getCurrentSeller().getPhoneNumber());
//        Text credit = new Text("credit: " + RegisterSellerMenu.getCurrentSeller().getCredit());
//        Text companyName = new Text("company name: " + RegisterSellerMenu.getCurrentSeller());

        title.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
//        username.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        firstName.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        lastName.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        email.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        phoneNumber.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        credit.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
//        companyName.setFont(Font.font("verdana", FontPosture.REGULAR, 10));

//        vBox1.getChildren().addAll(imageView, title, username, firstName, lastName, email, phoneNumber, credit, companyName);
        vBox1.getChildren().addAll(imageView, title, info);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setWalletScene() {
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
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                show();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);

        TextField textField = new TextField();
        textField.setPromptText("amount");
        Button button1 = new Button("charge wallet");
        Button button2 = new Button("withdrawal from wallet");

        Label notify = new Label();
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(textField, button1, button2, notify);
        button1.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    CWalletController.sellerWalletCharge(Double.parseDouble(textField.getText()));
                    notify.setText("seller wallet charged");
                    notify.setStyle("-fx-text-fill: #3193ff");
                } catch (Exception e) {
                    notify.setText(e.getMessage());
                    notify.setStyle("-fx-text-fill: #ff4f59");
                }
            }
        });

        button2.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    CWalletController.sellerWithdrawalWallet(Double.parseDouble(textField.getText()));
                    notify.setText("withdrawal from seller wallet is done");
                    notify.setStyle("-fx-text-fill: #3193ff");
                } catch (Exception e) {
                    notify.setText(e.getMessage());
                    notify.setStyle("-fx-text-fill: #ff4f59");
                }
            }
        });

    }

    public void setEditScene() {
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
        VBox vBox = new VBox(10);
        Label notify = new Label();
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                show();
            }
        });
        Label title = new Label("Editing field");
        vBox.getChildren().addAll(backButton, title);
        pane.setTop(vBox);
        HBox hBox = new HBox(10);
        ChoiceBox<String> field = new ChoiceBox<>();
        field.setStyle(style);
        field.getItems().addAll("first name", "last name", "password", "email", "phone number");
        TextField newContent = new TextField();
        newContent.setStyle(style);
        newContent.setPromptText("new content for this field");
        Button button = new Button("change");
        button.setStyle(style);
        hBox.getChildren().addAll(field, newContent, button);
        VBox vBox1 = new VBox(10);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                String data = field.getValue() + "," + newContent.getText();
                try {
                    CSellerController.editSellerInfo(data);
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("successfully changed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //SellerAbilitiesManager.changeField(RegisterSellerMenu.getCurrentSeller(), field.getValue(), newContent.getText());
//                notify.setStyle("-fx-text-fill: #3193ff");
//                notify.setText("successfully changed");
            }
        });
        vBox1.getChildren().addAll(hBox, notify);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 500, 500);
        Menu.window.setScene(scene);
    }

    public void setAddAuctionScene() {
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
        Label notify = new Label();
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.TOP_LEFT);

        VBox vBox = new VBox(10);
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        Label title = new Label("Add auction");

        TextField productID = new TextField();
        productID.setPromptText("product ID");

        TextField date = new TextField();
        date.setPromptText("yyyy-MM-dd_HH:mm");

        Button addAuction = new Button("Add auction");
        productID.setStyle(style);
        date.setStyle(style);

        addAuction.setStyle(style);

        addAuction.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                if (Product.getProductWithId(productID.getText()) != null) {
//                    if (Product.getProductWithId(productID.getText()).getSeller().equals(RegisterSellerMenu.getCurrentSeller())) {
//                        //SellerAbilitiesManager.addAuction(productID.getText(), date.getText());
//                        CSellerController.addAuction();
//                        SellerController.addAuction(productID.getText(), date.getText());
//                        notify.setStyle("-fx-text-fill: #3193ff");
//                        notify.setText("auction added successfully");
//                    } else {
//                        notify.setStyle("-fx-text-fill: #ff4f59");
//                        notify.setText("adding auction is only allowed for your own products");
//                    }
//                } else {
//                    notify.setStyle("-fx-text-fill: #ff4f59");
//                    notify.setText("there isn't any product with this id");
//                }
//            }
                String data = productID.getText() + " " + date.getText();
                try {
                    CSellerController.addAuction(data);
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("auction added successfully");
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                }

            }
        });

        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                show();
                notify.setText("");
            }
        });

        vBox.getChildren().addAll(backButton, title, productID, date, addAuction, notify);
        vBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        pane.setCenter(vBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setSalesHistoryScene() {
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
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                show();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
        //listView.getItems().addAll(SellerAbilitiesManager.viewSalesHistory(RegisterSellerMenu.getCurrentSeller()));
        try {
            listView.getItems().addAll(CSellerController.showSalesHistory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("show discount");
                alert.setHeaderText("discount information");
                try {
                    alert.setContentText(CSellerController.showLog(listView.getSelectionModel().getSelectedItem()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                String s = SellerAbilitiesManager.showLogInfo(listView.getSelectionModel().getSelectedItem());
//                alert.setContentText(s);
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.show();
            }
        });
        pane.setCenter(listView);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 350, 350);
        Menu.window.setScene(scene);
    }

    public void setManageProductScene() {
        //String path = "C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Sounds\\button.mp3";
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
        Label notify = new Label();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                show();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(SellerAbilitiesManager.showProducts(SellerController.getSeller()));
//        listView.getItems().addAll(CSellerController.showProducts());
        pane.setCenter(listView);

        VBox vBox1 = new VBox(10);
        Button addProduct = new Button("Add product");
        Button editProduct = new Button("Edit product");
        Button removeButton = new Button("Remove product");
        addProduct.setStyle(style);
        editProduct.setStyle(style);
        removeButton.setStyle(style);
        addProduct.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setAddProductScene();
            }
        });
        editProduct.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                selectedProduct = Product.getProductByName(listView.getSelectionModel().getSelectedItem());
                setEditProductScene();
            }
        });
        removeButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                try {
                    CSellerController.removeProductRequest(listView.getSelectionModel().getSelectedItem());
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("request sent");
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                }
//                selectedProduct = Product.getProductByName(listView.getSelectionModel().getSelectedItem());
//                try {
//                    SellerAbilitiesManager.sendRemovingProductRequest(selectedProduct, RegisterSellerMenu.getCurrentSeller());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("request sent");
            }
        });
        vBox1.getChildren().addAll(addProduct, editProduct, removeButton, notify);
        pane.setLeft(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setEditProductScene() {
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
        Label notify = new Label();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        back.setStyle(style);
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setManageProductScene();
            }
        });
        vBox.getChildren().addAll(back);
        pane.setTop(back);

        HBox hBox = new HBox(10);
        ChoiceBox<String> field = new ChoiceBox<>();
        field.setStyle(style);
        field.getItems().addAll("name", "company name", "description", "seller", "price", "category");
        TextField newContent = new TextField();
        newContent.setPromptText("new content for this field");
        newContent.setStyle(style);
        Button button = new Button("change");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();

                String data = field.getValue() + " " + newContent.getText();
                try {
                    CSellerController.editProductRequest(selectedProduct.getProductId(), data);
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("request sent");
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                }
//                try {
//                    SellerAbilitiesManager.sendEditingProductRequest(selectedProduct, RegisterSellerMenu.getCurrentSeller(), field.getValue(), newContent.getText());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                notify.setStyle("-fx-text-fill: #3193ff");
//                notify.setText("request sent");
            }
        });
        hBox.getChildren().addAll(field, newContent, button, notify);
        pane.setCenter(hBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 350, 350);
        Menu.window.setScene(scene);
    }

    public void setAddProductScene() {
        // String path = "C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Sounds\\button.mp3";
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
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        back.setStyle(style);
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setManageProductScene();
            }
        });
        vBox.getChildren().addAll(back);
        pane.setTop(back);
        VBox vBox1 = new VBox(10);
        Label notify = new Label();
        TextField ID = new TextField();
        ID.setPromptText("product ID");
        TextField name = new TextField();
        name.setPromptText("name");
        TextField company = new TextField();
        company.setPromptText("company");
        TextField price = new TextField();
        price.setPromptText("price");
        TextField category = new TextField();
        category.setPromptText("category");
        TextField explanation = new TextField();
        explanation.setPromptText("explanation");
        TextField feature = new TextField();
        feature.setPromptText("feature");
        TextField paths = new TextField();
        paths.setPromptText("path");
        Button add = new Button("add");
        ID.setStyle(style);
        name.setStyle(style);
        company.setStyle(style);
        price.setStyle(style);
        category.setStyle(style);
        explanation.setStyle(style);
        feature.setStyle(style);
        paths.setStyle(style);
        add.setStyle(style);
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String data = ID.getText() + "," + name.getText() + "," + company.getText() + "," + Double.parseDouble(price.getText()) + "," +
                        category.getText() + "," + explanation.getText() + "," + feature.getText() + "," + paths.getText();
                try {
                    CSellerController.addProductRequest(data);
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("request sent to manager");
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                }
//                try {
//                    SellerAbilitiesManager.addProduct(ID.getText(), name.getText(), company.getText(), Double.parseDouble(price.getText()),
//                            Category.getCategoryByName(category.getText()), RegisterSellerMenu.getCurrentSeller(), explanation.getText(), feature.getText(), paths.getText());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                notify.setStyle("-fx-text-fill: #3193ff");
//                notify.setText("request sent to manager");
            }
        });
        vBox1.getChildren().addAll(ID, name, company, price, category, explanation, feature, paths, add, notify);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setShowCategoriesScene() {
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
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                show();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
//        listView.getItems().addAll(SellerAbilitiesManager.showCategories());
        listView.getItems().addAll(CSellerController.showCategories());
        pane.setCenter(listView);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 500, 500);
        Menu.window.setScene(scene);
    }

    public void setOffsScene() {
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
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                show();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(CSellerController.showDiscounts());
        //listView.getItems().addAll(SellerAbilitiesManager.viewOffs(RegisterSellerMenu.getCurrentSeller()));
        pane.setCenter(listView);

        VBox vBox1 = new VBox(10);
        Button addOff = new Button("Add off");
        addOff.setStyle(style);
        Button editOff = new Button("Edit off");
        editOff.setStyle(style);
        addOff.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setAddOffScene();
            }
        });
        editOff.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedDiscount = Discount.getDiscountById(listView.getSelectionModel().getSelectedItem());
                setEditOffScene();
            }
        });
        vBox1.getChildren().addAll(addOff, editOff);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setEditOffScene() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\Test\\src\\main\\java\\Sounds\\button.mp3";
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
        Label notify = new Label();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        back.setStyle(style);
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setOffsScene();
            }
        });
        vBox.getChildren().addAll(back);
        pane.setTop(back);

        HBox hBox = new HBox(10);
        ChoiceBox<String> field = new ChoiceBox<>();
        field.setStyle(style);
        field.getItems().addAll("discount percent", "start date", "end date");
        TextField newContent = new TextField();
        newContent.setStyle(style);
        newContent.setPromptText("new content for this field");
        Button button = new Button("change");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                String data = field.getValue() + " " + newContent.getText();
                try {
                    CSellerController.editDiscountRequest(selectedDiscount.getDiscountId(), data);
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("request sent");
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                }
//                try {
//                    SellerAbilitiesManager.sendEditingOffRequest(selectedDiscount, RegisterSellerMenu.getCurrentSeller(), field.getValue(), newContent.getText());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                notify.setStyle("-fx-text-fill: #3193ff");
//                notify.setText("request sent");
            }
        });
        hBox.getChildren().addAll(field, newContent, button, notify);
        pane.setCenter(hBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 500, 500);
        Menu.window.setScene(scene);
    }

    public void setAddOffScene() {
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
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setOffsScene();
            }
        });
        back.setStyle(style);
        vBox.getChildren().addAll(back);
        pane.setTop(back);
        VBox vBox1 = new VBox(10);
        Label notify = new Label();
        TextField ID = new TextField();
        ID.setPromptText("off ID");
        TextField startDate = new TextField();
        startDate.setPromptText("yyyy-mm-dd");
        TextField endDate = new TextField();
        endDate.setPromptText("yyyy-mm-dd");
        TextField discountPercent = new TextField();
        discountPercent.setPromptText("discount percent");
        TextField productsName = new TextField();
        productsName.setPromptText("product name");
        Button add = new Button("Add");
        ID.setStyle(style);
        startDate.setStyle(style);
        endDate.setStyle(style);
        discountPercent.setStyle(style);
        productsName.setStyle(style);
        add.setStyle(style);
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                String data = ID.getText() + " " + startDate.getText() + " " + endDate.getText() + " " +
                        Double.parseDouble(discountPercent.getText()) + " " + productsName.getText();
                try {
                    CSellerController.addDiscountRequest(data);
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("request sent");
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                }
//                String s = productsName.getText();
//                if (Product.getProductByName(s) == null) {
//                    notify.setStyle("-fx-text-fill: #ff4f59");
//                    notify.setText("wrong product name");
//                } else {
//                    Discount discount = null;
//                    try {
//                        discount = SellerAbilitiesManager.addDiscount(ID.getText(), startDate.getText(), endDate.getText(),
//                                Double.parseDouble(discountPercent.getText()), s);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        new AddOffRequest(RegisterSellerMenu.getCurrentSeller(), discount);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    notify.setStyle("-fx-text-fill: #3193ff");
//                    notify.setText("request sent");
//                }
            }
        });
        vBox1.getChildren().

                addAll(ID, startDate, endDate, discountPercent, productsName, add, notify);
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