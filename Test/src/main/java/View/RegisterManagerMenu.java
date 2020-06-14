package View;

import Controller.RegisterAndLoginManager;
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

public class RegisterManagerMenu extends Menu {
    public static Manager currentManager;

    public RegisterManagerMenu(Menu parentMenu) {
        super("Register Manager", parentMenu);
    }

    @Override
    public void show() {
        BorderPane pane = new BorderPane();
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: #000000;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); -fx-font-size: 1.9em; -fx-padding: 10px;";
        HBox registerAndLoginButtons = new HBox(10);
        registerAndLoginButtons.setAlignment(Pos.CENTER);
        Button loginButton = new Button("Login");
        loginButton.setStyle(style);
        registerAndLoginButtons.getChildren().addAll(loginButton);
        HBox backAndOthersButton = new HBox(10);
        backAndOthersButton.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Back");
        backAndOthersButton.getChildren().addAll(backButton, registerAndLoginButtons);
        VBox vBox = new VBox(220);
        vBox.getChildren().addAll(backAndOthersButton, registerAndLoginButtons);
        addActionForButton(backButton, loginButton);
        vBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#abbaab , #ffffff)");
        pane.setCenter(vBox);
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void addActionForButton(Button backButton, Button loginButton) {
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
        loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                loginManagerScene();
            }
        });
    }
    public void loginManagerScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(10);
        Button backButton = new Button("Back");
        Label title = new Label("Login");
        Label notify = new Label();
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        Button loginButton = new Button("login");
        loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Manager.isThereManagerWithUserName(usernameTextField.getText())) {
                    if (RegisterAndLoginManager.isUserNameAndPasswordCorrectForManager(usernameTextField.getText(), passwordField.getText())) {
                        currentManager = Manager.getManagerByUserName(usernameTextField.getText());
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
                parentMenu.show();
            }
        });
        vBox.getChildren().addAll(backButton, title, usernameTextField, passwordField, loginButton, notify);
        vBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#abbaab , #ffffff)");
        pane.setCenter(vBox);
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public static Manager getCurrentManager() {
        return currentManager;
    }

    public static void setCurrentManager(Manager currentManager) {
        RegisterManagerMenu.currentManager = currentManager;
    }
}


