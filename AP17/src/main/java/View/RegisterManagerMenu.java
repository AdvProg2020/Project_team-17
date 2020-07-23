package View;

import Client.ClientController.CRegisterAndLoginController;
import Models.Accounts.Manager;
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
import java.util.ArrayList;
import java.util.HashMap;

public class RegisterManagerMenu extends Menu {
    public static Manager currentManager;
    public static ArrayList<Manager> onlineManagers = new ArrayList<>();

    public RegisterManagerMenu(Menu parentMenu) {
        super("Register Manager", parentMenu);
    }

    @Override
    public void show() {
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
        HBox registerAndLoginButtons = new HBox(10);
        registerAndLoginButtons.setAlignment(Pos.CENTER);
        Button loginButton = new Button("Login");
        loginButton.setStyle(style);
        registerAndLoginButtons.getChildren().addAll(loginButton);
        HBox backAndOthersButton = new HBox(10);
        backAndOthersButton.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        backAndOthersButton.getChildren().addAll(backButton, registerAndLoginButtons);
        VBox vBox = new VBox(220);
        vBox.getChildren().addAll(backAndOthersButton, registerAndLoginButtons);
        addActionForButton(mediaPlayer, backButton, loginButton);
        pane.setCenter(vBox);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void addActionForButton(MediaPlayer mediaPlayer, Button backButton, Button loginButton) {
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
                loginManagerScene();
            }
        });
    }

    public void loginManagerScene() {
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
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        Label title = new Label("Login");
        Label notify = new Label();
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        Button loginButton = new Button("login");
        usernameTextField.setStyle(style);
        passwordField.setStyle(style);
        loginButton.setStyle(style);
        loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                String dataToSend = usernameTextField.getText() + " " + passwordField.getText();
                try {
                    CRegisterAndLoginController.loginManager(dataToSend);
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("successfully signed in");
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                }
                //              try {
//                    CRegisterAndLoginController.login(dataToSend);
//                if (Manager.isThereManagerWithUserName(usernameTextField.getText())) {
//                    if (RegisterAndLoginManager.isUserNameAndPasswordCorrectForManager(usernameTextField.getText(), passwordField.getText())) {
//                        CManagerController.loginManager(usernameTextField.getText());
//                        currentManager = Manager.getManagerByUserName(usernameTextField.getText());
//                        System.out.println(currentManager.getUserName());
//                        onlineManagers.add(currentManager);
//                        notify.setStyle("-fx-text-fill: #3193ff");
//                        notify.setText("successfully signed in");
//                    } else {
//                        notify.setStyle("-fx-text-fill: #ff4f59");
//                        notify.setText("password is wrong");
//                    }
//                } else {
//                    notify.setStyle("-fx-text-fill: #ff4f59");
//                    notify.setText("there isn't any manager with this username");
//                }
//            }
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

    public static Manager getCurrentManager() {
        return currentManager;
    }

    public static void setCurrentManager(Manager currentManager) {
        RegisterManagerMenu.currentManager = currentManager;
    }

    public static void removeFromOnlineManager(Manager manager) {
        onlineManagers.remove(manager);
    }

    public static ArrayList<Manager> getOnlineManagers() {
        return onlineManagers;
    }
}


