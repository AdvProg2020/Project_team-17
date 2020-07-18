package View;

import Controller.RegisterAndLoginManager;
import Models.Accounts.Customer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

import java.io.IOException;


public class RegisterCustomerMenu extends Menu {
    public static Customer currentCustomer;

    public RegisterCustomerMenu(Menu parentMenu) {
        super("Register Seller", parentMenu);
    }

    @Override
    public void show() {
        String path = "C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        //String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        BorderPane pane = new BorderPane();
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), " +
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#dddddd 0%, #f6f6f6 50%);" +
                " -fx-background-radius: 8,7,6; " +
                "-fx-background-insets: 0,1,2; " +
                "-fx-text-fill: #000000;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); " +
                "-fx-font-size: 1.2em; " +
                "-fx-padding: 4px;";
        HBox registerAndLoginButtons = new HBox(10);
        registerAndLoginButtons.setAlignment(Pos.CENTER);
        Button registerButton = new Button("Register");
        registerButton.setStyle(style);
        Button loginButton = new Button("Login");
        loginButton.setStyle(style);
        registerAndLoginButtons.getChildren().addAll(registerButton, loginButton);
        HBox backAndOthersButton = new HBox(10);
        backAndOthersButton.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Back");
        backAndOthersButton.getChildren().addAll(backButton, registerAndLoginButtons);
        VBox vBox = new VBox(220);
        vBox.getChildren().addAll(backAndOthersButton, registerAndLoginButtons);
        addActionForButton(mediaPlayer, backButton, loginButton, registerButton);
        pane.setCenter(vBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void addActionForButton(MediaPlayer mediaPlayer, Button backButton, Button loginButton, Button registerButton) {
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                parentMenu.show();
            }
        });
        loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                loginCustomerScene();
            }
        });
        registerButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                registerCustomerScene();
            }
        });
    }


    public void registerCustomerScene() {
        String path = "C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        //String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        BorderPane pane = new BorderPane();
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), " +
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#dddddd 0%, #f6f6f6 50%);" +
                " -fx-background-radius: 8,7,6; " +
                "-fx-background-insets: 0,1,2; " +
                "-fx-text-fill: #000000;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); " +
                "-fx-font-size: 1.2em; " +
                "-fx-padding: 4px;";
        VBox vBox = new VBox(10);
        Button backButton = new Button("Back");
        Label title = new Label("Customer account registration");
        Label notify = new Label();
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

        TextField extraTextField = new TextField();
        extraTextField.setPromptText("credit");

        TextField paths = new TextField();
        paths.setPromptText("path");

        Button SUButton = new Button("Sign up");
        backButton.setStyle(style);
        userNameTextField.setStyle(style);
        firstNameTextField.setStyle(style);
        lastNameTextField.setStyle(style);
        emailTextField.setStyle(style);
        phoneNumberTextField.setStyle(style);
        addressTextField.setStyle(style);
        extraTextField.setStyle(style);
        SUButton.setStyle(style);
        paths.setStyle(style);

        SUButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                if (RegisterAndLoginManager.canHaveAccountWithThisUsername(userNameTextField.getText())) {
                    try {
                        new Customer(userNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(),
                                phoneNumberTextField.getText(), passwordField.getText(), Double.parseDouble(extraTextField.getText()), addressTextField.getText(), paths.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    try {
//                        WriteIntoFile.writeCustomersIntoFile();
//                    } catch (IOException e) {
//                        System.err.println(e.getMessage());
//                    }
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
                parentMenu.show();
            }
        });

        vBox.getChildren().addAll(backButton, title, userNameTextField, passwordField, firstNameTextField, lastNameTextField, emailTextField, phoneNumberTextField, addressTextField, extraTextField, paths, SUButton, notify);
        pane.setCenter(vBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void loginCustomerScene() {
        String path = "C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        //  String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        BorderPane pane = new BorderPane();
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), " +
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#dddddd 0%, #f6f6f6 50%);" +
                " -fx-background-radius: 8,7,6; " +
                "-fx-background-insets: 0,1,2; " +
                "-fx-text-fill: #000000;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); " +
                "-fx-font-size: 1.2em; " +
                "-fx-padding: 4px;";
        VBox vBox = new VBox(10);
        Button backButton = new Button("Back");
        Label title = new Label("Login");
        Label notify = new Label();
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        Button loginButton = new Button("login");
        backButton.setStyle(style);
        usernameTextField.setStyle(style);
        passwordField.setStyle(style);
        loginButton.setStyle(style);
        loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                if (Customer.isThereCustomerWithUserName(usernameTextField.getText())) {
                    if (RegisterAndLoginManager.isUserNameAndPasswordCorrectForCustomer(usernameTextField.getText(), passwordField.getText())) {
                        currentCustomer = Customer.getCustomerByName(usernameTextField.getText());
                        notify.setStyle("-fx-text-fill: #3193ff");
                        notify.setText("successfully signed in");
                    } else {
                        notify.setStyle("-fx-text-fill: #ff4f59");
                        notify.setText("password is wrong");
                    }
                }
            }
        });
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                parentMenu.show();
            }
        });
        vBox.getChildren().addAll(backButton, title, usernameTextField, passwordField, loginButton, notify);
        pane.setCenter(vBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer currentCustomer) {
        RegisterCustomerMenu.currentCustomer = currentCustomer;
    }
}
