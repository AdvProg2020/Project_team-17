package View;

import Client.ClientController.CRegisterAndLoginController;
import Controller.RegisterAndLoginManager;
import Controller.WriteIntoFile;
import Models.Accounts.Manager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class MainMenu extends Menu {


    public MainMenu() {
        super("Main Menu", null);
    }

    public BorderPane setMainScene() {
        //String path = "C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        BorderPane mainPane = new BorderPane();
        VBox mainButtons = new VBox(15);
        mainButtons.setAlignment(Pos.CENTER);
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), " +
                "linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#cdded5 0%, #f6f6f6 50%); " +
                "-fx-background-radius: 8,7,6; " +
                "-fx-background-insets: 0,1,2; " +
                "-fx-text-fill: #3193ff;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
                " -fx-font-size: 1.4em;" +
                " -fx-padding: 8px;";
        Button accountsButton = new Button("Accounts");
        accountsButton.setStyle(style);
        Button productsButton = new Button("Products");
        productsButton.setStyle(style);
        Button discountButton = new Button("Discounts");
        discountButton.setStyle(style);
        Button exitButton = new Button("Exit");
        exitButton.setStyle(style);
        Image mainImage = null;
        try {
            //mainImage = new Image(new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Images\\MAIN_BACKGROUND.png"));
            mainImage = new Image(new FileInputStream("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Images\\MAIN_BACKGROUND.png"));

        } catch (Exception e) {

        }
        BackgroundImage mainBackgroundImage = new BackgroundImage(mainImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background mainBackground = new Background(mainBackgroundImage);
        addActionForButtons(mediaPlayer, accountsButton, productsButton, discountButton, exitButton);
        mainButtons.getChildren().addAll(accountsButton, productsButton, discountButton, exitButton);
        mainButtons.setBackground(mainBackground);
        mainPane.setCenter(mainButtons);
        return mainPane;
    }

    public void addActionForButtons(MediaPlayer mediaPlayer, Button accountButton, Button productButton, Button discountButton, Button exitButton) {
        accountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                handleAccounts();
            }
        });
        productButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
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
        exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                try {
                    System.out.println("a");
                    WriteIntoFile.writeIntoFile(new Gson());
                    System.out.println("b");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("c");
                Platform.exit();
                System.out.println("d");
                System.exit(0);
                System.out.println("e");
            }
        });
    }

    public void handleAccounts() {
        AccountsMenu accountsMenu = new AccountsMenu(this);
        accountsMenu.show();
    }

    public void handleProductsMenu() {
        ProductsMenu productsMenu = new ProductsMenu(this);
        productsMenu.show();
    }

    public void handleDiscountsMenu() {
        DiscountsMenu discountsMenu = new DiscountsMenu(this);
        discountsMenu.show();
    }

    public void registerFirstManager() {
        //String path = "C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
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
        Label title = new Label("Manager account registration");
        Label notify = new Label();
        Button button = new Button("go to main page");
        button.setStyle(style);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                setMainScene();
                Scene scene = new Scene(setMainScene(), 600, 600);
                Menu.window.setScene(scene);
                Menu.window.show();
            }
        });
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
        firstNameTextField.setStyle(style);
        lastNameTextField.setStyle(style);
        passwordField.setStyle(style);
        emailTextField.setStyle(style);
        phoneNumberTextField.setStyle(style);
        addressTextField.setStyle(style);
        SUButton.setStyle(style);
        paths.setStyle(style);

        SUButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                String data = userNameTextField.getText() + " " + firstNameTextField.getText() + " " + lastNameTextField.getText() + " " + emailTextField.getText() + " " +
                        " " + phoneNumberTextField.getText() + " " + passwordField.getText() + " " + paths.getText();
                try {
                    CRegisterAndLoginController.registerManager(data);
//                if (RegisterAndLoginManager.canHaveAccountWithThisUsername(userNameTextField.getText())) {
//                    CManagerController.registerManager(userNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(),
//                            phoneNumberTextField.getText(), passwordField.getText(), paths.getText());
//                    try {
//                        new Manager(userNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(),
//                                phoneNumberTextField.getText(), passwordField.getText(), paths.getText());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        WriteIntoFile.writeManagersIntoFile();
//                    } catch (IOException e) {
//                        System.err.println(e.getMessage());
//                    }
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("successfully registered");
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                    e.printStackTrace();
                }
//                } else {
//                    notify.setStyle("-fx-text-fill: #ff4f59");
//                    notify.setText("this username already exist");
//                }

            }
        });

        vBox.getChildren().addAll(button, title, userNameTextField, passwordField, firstNameTextField, lastNameTextField, emailTextField, phoneNumberTextField, addressTextField, paths, SUButton, notify);
        pane.setCenter(vBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

   /* MediaPlayer mediaPlayer;
    private void music() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\My Song.m4a";
        //String path = "C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Sounds\\My Song.m4a";
        Media media = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }*/


    @Override
    public void show() {
        if (Manager.getAllManagers().size() != 0) {
            // music();
            setMainScene();
            Scene scene = new Scene(setMainScene(), 600, 600);
            Menu.window.setScene(scene);
            Menu.window.show();
        } else {
            // music();
            registerFirstManager();
            Menu.window.show();

        }
    }

}