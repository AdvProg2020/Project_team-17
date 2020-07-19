package View.AccountMenus;

import Controller.AccountsManager.ManagerAbilitiesManager;
import Controller.RegisterAndLoginManager;
import Models.Accounts.Customer;
import Models.Category;
import Models.DiscountCode;
import Server.ServerController.AccountsController.ManagerController;
import Client.ClientController.AccountsController.CManagerController;
import View.*;
import View.Menu;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class ManagerMenu extends Menu {
    Category selectedCategory;
    DiscountCode selectedDiscountCode;

    public ManagerMenu(Menu parentMenu) {
        super("Manager ", parentMenu);
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
        Button manageUsers = new Button("Manage users");
        manageUsers.setStyle(style);
        manageUsers.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setManageUsersScene();
            }
        });

        Button manageProducts = new Button("Manage products");
        manageProducts.setStyle(style);
        manageProducts.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setManageProductsScene();
            }
        });
        Button manageDiscountCodes = new Button("Manage discount codes");
        manageDiscountCodes.setStyle(style);
        manageDiscountCodes.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setDiscountCodeScene();
            }
        });

        Button manageRequests = new Button("Manage requests");
        manageRequests.setStyle(style);
        manageRequests.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                manageRequestsScene();
            }
        });

        Button manageCategories = new Button("Manage categories");
        manageCategories.setStyle(style);
        manageCategories.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setCategoriesScene();
            }
        });

        Button defineLeastAmount = new Button("Define wallet least amount");
        defineLeastAmount.setStyle(style);
        defineLeastAmount.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setLeastAmount();
            }
        });

        Button editButton = new Button("Edit field");
        editButton.setStyle(style);
        editButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEditScene();
            }
        });

        Button logs = new Button("Show logs");
        logs.setStyle(style);
        logs.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setLogsScene();
            }
        });


        vBox.getChildren().addAll(backButton, manageUsers, manageProducts, manageDiscountCodes, manageRequests, manageCategories, editButton, defineLeastAmount, logs);
        pane.setLeft(vBox);
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        Image image = null;
        try {
            FileInputStream inputStream = new FileInputStream(RegisterSellerMenu.getCurrentSeller().getPath());
            image = new Image(inputStream);
        } catch (Exception e) {
        }
        ImageView imageView = new ImageView(image);
        Text title = new Text("MANAGER");
        Text username = new Text("username: " + RegisterManagerMenu.getCurrentManager().getUserName());
        Text firstName = new Text("first name: " + RegisterManagerMenu.getCurrentManager().getFirstName());
        Text lastName = new Text("last name: " + RegisterManagerMenu.getCurrentManager().getLastName());
        Text email = new Text("email: " + RegisterManagerMenu.getCurrentManager().getEmail());
        Text phoneNumber = new Text("phone number: " + RegisterManagerMenu.getCurrentManager().getPhoneNumber());
        title.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR, 12));
        username.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
        firstName.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
        lastName.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
        email.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
        phoneNumber.setFont(Font.font("verdana", FontPosture.REGULAR, 10));
        vBox1.getChildren().addAll(imageView, title, username, firstName, lastName, email, phoneNumber);
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

    public void setEditScene() {
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
                try {
                    CManagerController.editManagerInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ManagerController.editManagerInfo(field.getValue(), newContent.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // ManagerAbilitiesManager.changeField(RegisterManagerMenu.getCurrentManager(), field.getValue(), newContent.getText());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("successfully changed");
            }
        });

        vBox1.getChildren().addAll(hBox, notify);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 500, 500);
        Menu.window.setScene(scene);
    }

    public void setLeastAmount() {
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
        Label title = new Label("Define least amount for wallet");
        vBox.getChildren().addAll(backButton, title);
        pane.setTop(vBox);
        HBox hBox = new HBox(10);
        TextField textField = new TextField();
        textField.setStyle(style);
        textField.setPromptText("define least amount for wallet");
        Button button = new Button("set");
        button.setStyle(style);
        hBox.getChildren().addAll(textField, button);
        VBox vBox1 = new VBox(10);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    CManagerController.defineLeastAmount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ManagerController.defineLeastAmountForSellerAndCustomerWallet(Double.parseDouble(textField.getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // ManagerAbilitiesManager.changeField(RegisterManagerMenu.getCurrentManager(), field.getValue(), newContent.getText());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("successfully defined");
            }
        });

        vBox1.getChildren().addAll(hBox, notify);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 500, 500);
        Menu.window.setScene(scene);
    }

    public void setManageUsersScene() {
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
                mediaPlayer.play();
                show();
                notify.setText("");
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(ManagerAbilitiesManager.showAllAccounts());
        pane.setCenter(listView);

        VBox vBox1 = new VBox(10);
        Button view = new Button("View account");
        Button delete = new Button("Delete account");
        Button addManager = new Button("Add manager");
        Button addSupporter = new Button("Add supporter");
        Button onlineAccounts = new Button("Users status");
        onlineAccounts.setStyle(style);
        view.setStyle(style);
        delete.setStyle(style);
        addManager.setStyle(style);
        addSupporter.setStyle(style);
        view.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("show user info");
                alert.setHeaderText("user information");
                try {
                    CManagerController.showAccountDetails();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //String s = ManagerAbilitiesManager.viewAccountByUsername(listView.getSelectionModel().getSelectedItem());
                //alert.setContentText(s);
                alert.setContentText(ManagerController.showAccountInfo(listView.getSelectionModel().getSelectedItem()));
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.show();
                notify.setText("");
            }
        });

        onlineAccounts.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("show user status");
                alert.setHeaderText("user information");
                try {
                    CManagerController.showAccountStatus();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //String s = ManagerAbilitiesManager.viewAccountByUsername(listView.getSelectionModel().getSelectedItem());
                //alert.setContentText(s);
                alert.setContentText(ManagerController.showUserStatusInfo(listView.getSelectionModel().getSelectedItem()));
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.show();
                notify.setText("");
            }

        });

        delete.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                CManagerController.deleteAccount();
                try {
                    ManagerController.deleteUser(listView.getSelectionModel().getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // ManagerAbilitiesManager.deleteUser(listView.getSelectionModel().getSelectedItem());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("user deleted successfully");
            }
        });

        addManager.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setAddManagerScene();
                notify.setText("");
            }
        });
        addSupporter.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setAddSupporterScene();
                notify.setText("");
            }
        });

        vBox1.getChildren().addAll(view, delete, addManager);
        pane.setLeft(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setAddManagerScene() {
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
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.TOP_LEFT);

        VBox vBox = new VBox(10);
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        Label title = new Label("Manager account registration");
        TextField userNameTextField = new TextField();
        userNameTextField.setPromptText("username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");

        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("first name");

        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("last name");

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("email");

        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("phone number");

        TextField paths = new TextField();
        paths.setPromptText("paths");

        TextField addressTextField = new TextField();
        addressTextField.setPromptText("address");

        Button SUButton = new Button("Sign up");
        userNameTextField.setStyle(style);
        passwordField.setStyle(style);
        firstNameTextField.setStyle(style);
        lastNameTextField.setStyle(style);
        emailTextField.setStyle(style);
        addressTextField.setStyle(style);
        phoneNumberTextField.setStyle(style);
        paths.setStyle(style);
        SUButton.setStyle(style);

        SUButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (RegisterAndLoginManager.canHaveAccountWithThisUsername(userNameTextField.getText())) {
//                    try {
//                        ManagerAbilitiesManager.createAnotherManager(userNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(),
//                                phoneNumberTextField.getText(), passwordField.getText());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    try {
                        CManagerController.addManager();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        ManagerController.addManager(userNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText()
                                , phoneNumberTextField.getText(), passwordField.getText(), paths.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("successfully registered");
                } else {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText("this username already exist");
                }
            }
        });

        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setManageUsersScene();
                notify.setText("");
            }
        });

        vBox.getChildren().addAll(backButton, title, userNameTextField, passwordField, firstNameTextField, lastNameTextField, emailTextField, phoneNumberTextField, addressTextField, paths, SUButton, notify);
        vBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        pane.setCenter(vBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setAddSupporterScene() {
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
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.TOP_LEFT);

        VBox vBox = new VBox(10);
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        Label title = new Label("Supporter account registration");
        TextField userNameTextField = new TextField();
        userNameTextField.setPromptText("username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");

        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("first name");

        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("last name");

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("email");

        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("phone number");

        TextField addressTextField = new TextField();
        addressTextField.setPromptText("address");

        TextField paths = new TextField();
        paths.setPromptText("path");

        Button SUButton = new Button("Sign up");
        userNameTextField.setStyle(style);
        passwordField.setStyle(style);
        firstNameTextField.setStyle(style);
        lastNameTextField.setStyle(style);
        emailTextField.setStyle(style);
        addressTextField.setStyle(style);
        phoneNumberTextField.setStyle(style);
        SUButton.setStyle(style);

        SUButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (RegisterAndLoginManager.canHaveAccountWithThisUsername(userNameTextField.getText())) {
//                    try {
//                        ManagerAbilitiesManager.createAnotherManager(userNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(),
//                                phoneNumberTextField.getText(), passwordField.getText());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    try {
                        CManagerController.addSupporter();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        ManagerController.addSupporter(userNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText()
                                , phoneNumberTextField.getText(), passwordField.getText(), paths.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("successfully registered");
                } else {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText("this username already exist");
                }
            }
        });
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setManageUsersScene();
                notify.setText("");
            }
        });

        vBox.getChildren().addAll(backButton, title, userNameTextField, passwordField, firstNameTextField, lastNameTextField, emailTextField, phoneNumberTextField, addressTextField, SUButton, notify);
        vBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        pane.setCenter(vBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setDiscountCodeScene() {
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
                mediaPlayer.play();
                show();
            }
        });

        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(ManagerAbilitiesManager.viewDiscountCodes());
        pane.setCenter(listView);
        VBox vBox1 = new VBox(10);
        Button view = new Button("view discount code");
        Button edit = new Button("edit discount code");
        Button add = new Button("add discount code");
        Button remove = new Button("remove discount code");
        view.setStyle(style);
        edit.setStyle(style);
        add.setStyle(style);
        remove.setStyle(style);

        view.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("discount code info");
                alert.setHeaderText("discount code information");
                try {
                    CManagerController.showDiscountCodeDetails();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ManagerController.showDiscountCodeInfo(listView.getSelectionModel().getSelectedItem());
//                String s = ManagerAbilitiesManager.viewDiscountCode(listView.getSelectionModel().getSelectedItem());
//                alert.setContentText(s);
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.show();
            }
        });
        edit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                selectedDiscountCode = DiscountCode.getDiscountCodeWithCode(listView.getSelectionModel().getSelectedItem());
                editDiscountCodeScene();
            }
        });

        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                addDiscountCodeScene();
            }
        });

        remove.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                CManagerController.deleteDiscountCode();
                ManagerController.deleteDiscountCode(listView.getSelectionModel().getSelectedItem());
                //ManagerAbilitiesManager.removeDiscountCode(listView.getSelectionModel().getSelectedItem());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("discount code removed successfully");
            }
        });

        vBox1.getChildren().addAll(edit, add, remove, notify);
        pane.setLeft(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setLogsScene() {
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
                mediaPlayer.play();
                show();
            }
        });

        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(ManagerAbilitiesManager.showAllLogs());
        pane.setCenter(listView);
        VBox vBox1 = new VBox(10);
        Button view = new Button("view log");
        view.setStyle(style);

        view.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("log info");
                alert.setHeaderText("log information");
                try {
                    CManagerController.showLogDetails();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ManagerController.showLogInfo(listView.getSelectionModel().getSelectedItem());
//                String s = ManagerAbilitiesManager.viewDiscountCode(listView.getSelectionModel().getSelectedItem());
//                alert.setContentText(s);
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.show();
            }
        });

        vBox1.getChildren().addAll(view);
        pane.setLeft(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void editDiscountCodeScene() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        BorderPane pane = new BorderPane();
        Label notify = new Label();
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
        HBox hBox = new HBox(10);
        ChoiceBox<String> field = new ChoiceBox<>();
        field.setStyle(style);
        field.getItems().addAll("starting date", "ending date", "discount percent", "maximum discount amount", "count discount code");
        TextField newContent = new TextField();
        newContent.setStyle(style);
        newContent.setPromptText("new content for this field");
        Button change = new Button("change");
        change.setStyle(style);
        hBox.getChildren().addAll(field, newContent, change);
        VBox vBox1 = new VBox(10);
        change.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                CManagerController.editDiscountCode();
                ManagerController.editDiscountCodeInfo(selectedDiscountCode, field.getValue(), newContent.getText());
                //ManagerAbilitiesManager.editDiscountCode(selectedDiscountCode, field.getValue(), newContent.getText());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("successfully changed");
            }
        });
        vBox1.getChildren().addAll(hBox, notify);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 350, 350);
        Menu.window.setScene(scene);
    }

    public void addDiscountCodeScene() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        BorderPane pane = new BorderPane();
        Label notify = new Label();
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), " +
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#cdded5 0%, #f6f6f6 50%);" +
                " -fx-background-radius: 8,7,6; " +
                "-fx-background-insets: 0,1,2; " +
                "-fx-text-fill: #3193ff;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); " +
                "-fx-font-size: 1.2em; " +
                "-fx-padding: 4px;";
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                show();
            }
        });
        vBox1.getChildren().addAll(button);
        pane.setTop(vBox1);
        VBox vBox = new VBox(10);
        TextField ID = new TextField();
        ID.setPromptText("off ID");
        TextField startDate = new TextField();
        startDate.setPromptText("yyyy-mm-dd");
        TextField endDate = new TextField();
        endDate.setPromptText("yyyy-mm-dd");
        TextField discountPercent = new TextField();
        discountPercent.setPromptText("discount percent");
        TextField max = new TextField();
        max.setPromptText("maximum discount amount");
        TextField count = new TextField();
        count.setPromptText("number of repetitions");
        TextField customersName = new TextField();
        customersName.setPromptText("customer name");
        Button add = new Button("Add");
        ID.setStyle(style);
        startDate.setStyle(style);
        endDate.setStyle(style);
        discountPercent.setStyle(style);
        max.setStyle(style);
        count.setStyle(style);
        customersName.setStyle(style);
        add.setStyle(style);
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String s = customersName.getText();
                if (Customer.getCustomerByName(s) == null) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText("wrong customer name");
                } else {
                    //ManagerAbilitiesManager.createDiscountCode(ID.getText(), startDate.getText(), endDate.getText(), discountPercent.getText(), max.getText(), Integer.parseInt(count.getText()), s);
//                    try {
//                       // WriteIntoFile.writeDiscountCodesIntoFile();
//                   // } catch (IOException e) {
//                        System.err.println(e.getMessage());
                    CManagerController.addDiscountCode();
                    ManagerController.addDiscountCode(ID.getText(), startDate.getText(), endDate.getText(), discountPercent.getText(), max.getText(), Integer.parseInt(count.getText()), s);
//                    }
                    notify.setStyle("-fx-text-fill: #1593ff");
                    notify.setText("discount code created");

                }
            }
        });
        vBox.getChildren().addAll(ID, startDate, endDate, discountPercent, max, count, customersName, add, notify);
        pane.setCenter(vBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void manageRequestsScene() {
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
                mediaPlayer.play();
                show();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(ManagerAbilitiesManager.showAllRequests());
        pane.setCenter(listView);
        VBox vBox1 = new VBox(10);
        Button details = new Button("details");
        Button accept = new Button("accept");
        Button decline = new Button("decline");
        details.setStyle(style);
        accept.setStyle(style);
        decline.setStyle(style);
        details.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("show request info");
                alert.setHeaderText("request information");
                CManagerController.showRequest();
                ManagerController.showRequest(listView.getSelectionModel().getSelectedItem());
//                String s = ManagerAbilitiesManager.showDetailsOfRequest(listView.getSelectionModel().getSelectedItem());
//                alert.setContentText(s);
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.show();
            }
        });

        accept.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                ManagerAbilitiesManager.acceptRequest(listView.getSelectionModel().getSelectedItem());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("request accepted");
            }
        });

        decline.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                ManagerAbilitiesManager.declineRequest(listView.getSelectionModel().getSelectedItem());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("request declined");
            }
        });

        vBox1.getChildren().addAll(details, accept, decline, notify);
        pane.setLeft(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setCategoriesScene() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        BorderPane pane = new BorderPane();
        Label notify = new Label();
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
                notify.setText("");
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(ManagerAbilitiesManager.showAllCategories());
        pane.setCenter(listView);
        VBox vBox1 = new VBox(10);
        Button edit = new Button("edit category");
        Button add = new Button("add category");
        Button remove = new Button("remove category");
        edit.setStyle(style);
        add.setStyle(style);
        remove.setStyle(style);
        edit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                selectedCategory = Category.getCategoryByName(listView.getSelectionModel().getSelectedItem());
                setEditCategoryScene();
            }
        });

        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                addCategoryScene();
            }
        });

        remove.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                ManagerAbilitiesManager.removeCategory(listView.getSelectionModel().getSelectedItem());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("category removed successfully");
            }
        });
        vBox1.getChildren().addAll(edit, add, remove, notify);
        pane.setLeft(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void addCategoryScene() {
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
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setCategoriesScene();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        HBox hBox = new HBox(10);
        TextField name = new TextField();
        name.setPromptText("category name");
        TextField feature = new TextField();
        feature.setPromptText("category feature");
        Button add = new Button("Add category");
        name.setStyle(style);
        feature.setStyle(style);
        add.setStyle(style);
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                CManagerController.addCategory();
                ManagerController.addCategory(name.getText(), feature.getText());
//                ManagerAbilitiesManager.addCategory(name.getText(), feature.getText());
//                try {
//                    //WriteIntoFile.writeCategoriesIntoFile();
//                } catch (IOException e) {
//                    System.err.println(e.getMessage());
//                }
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("category successfully added");
            }
        });

        hBox.getChildren().addAll(name, feature, add, notify);
        pane.setCenter(hBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 500, 500);
        Menu.window.setScene(scene);
    }

    public void setEditCategoryScene() {
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
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        back.setStyle(style);
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setCategoriesScene();
            }
        });

        vBox.getChildren().addAll(back);
        pane.setTop(vBox);
        HBox hBox = new HBox(10);
        ChoiceBox<String> field = new ChoiceBox<>();
        field.setStyle(style);
        field.getItems().addAll("feature", "name");
        TextField newContent = new TextField();
        newContent.setPromptText("new content for this field");
        Button button = new Button("change");
        newContent.setStyle(style);
        button.setStyle(style);
        hBox.getChildren().addAll(field, newContent, button);
        VBox vBox1 = new VBox(10);


        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                CManagerController.editCategory();
                ManagerController.editCategory(selectedCategory, field.getValue(), newContent.getText());
                //ManagerAbilitiesManager.editCategory(selectedCategory, field.getValue(), newContent.getText());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("successfully changed");
            }
        });
        vBox1.getChildren().addAll(hBox, notify);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 500, 500);
        Menu.window.setScene(scene);
    }

    public void setManageProductsScene() {
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
                mediaPlayer.play();
                show();
            }
        });

        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(ManagerAbilitiesManager.showAllProducts());
        pane.setCenter(listView);
        VBox vBox1 = new VBox(10);
        Button remove = new Button("remove product");
        remove.setStyle(style);

        remove.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                // ManagerAbilitiesManager.removeProduct(listView.getSelectionModel().getSelectedItem());
                CManagerController.deleteProduct();
                ManagerController.deleteProduct(listView.getSelectionModel().getSelectedItem());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("category removed successfully");
            }
        });

        vBox1.getChildren().addAll(remove, notify);
        pane.setLeft(vBox1);
        Scene scene = new Scene(pane, 600, 600);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Menu.window.setScene(scene);
    }
}