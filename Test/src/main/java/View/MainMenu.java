package View;

import Controller.RegisterAndLoginManager;
import Models.Accounts.Manager;
import View.AccountMenus.CustomerMenu;
import View.AccountMenus.ManagerMenu;
import View.AccountMenus.SellerMenu;
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

import java.io.FileInputStream;

public class MainMenu extends Menu {


    public MainMenu() {
        super("Main Menu", null);
    }

    public BorderPane setMainScene() {
        BorderPane mainPane = new BorderPane();
        VBox mainButtons = new VBox(15);
        mainButtons.setAlignment(Pos.CENTER);
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#cdded5 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: #3193ff;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); -fx-font-size: 1.4em; -fx-padding: 8px;";
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
            mainImage = new Image(new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\" +
                    "Project_team-17\\Project_team-17\\Project_team-17\\project_phase2\\Test\\src\\main\\java\\Images\\MAIN_BACKGROUND.png"));

        } catch (Exception e) {

        }
        BackgroundImage mainBackgroundImage = new BackgroundImage(mainImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background mainBackground = new Background(mainBackgroundImage);
        addActionForButtons(accountsButton, productsButton, discountButton, exitButton);
        mainButtons.getChildren().addAll(accountsButton, productsButton, discountButton, exitButton);
        mainButtons.setBackground(mainBackground);
        mainPane.setCenter(mainButtons);
        return mainPane;
    }

    public void addActionForButtons(Button accountButton, Button productButton, Button discountButton, Button exitButton) {
        accountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleAccounts();
            }
        });
        productButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        discountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
    }

    public void handleAccounts() {
        AccountsMenu accountsMenu = new AccountsMenu(this);
        accountsMenu.show();
    }

    public void registerFirstManager() {
        BorderPane pane= new BorderPane();
        VBox vBox = new VBox(10);
        Label title = new Label("Manager account registration");
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

        Button SUButton = new Button("Sign up");

        SUButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (RegisterAndLoginManager.canHaveAccountWithThisUsername(userNameTextField.getText())) {
                    new Manager(userNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(),
                            phoneNumberTextField.getText(), passwordField.getText());
                    notify.setStyle("-fx-text-fill: #3193ff");
                    notify.setText("successfully registered");
                } else {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText("this username already exist");
                }

            }
        });

        vBox.getChildren().addAll(title, userNameTextField, passwordField, firstNameTextField, lastNameTextField, emailTextField, phoneNumberTextField, addressTextField, SUButton, notify);
        vBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#abbaab , #ffffff)");
        pane.setCenter(vBox);
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }


    @Override
    public void show() {
        //  if (Manager.getAllManagers().size()!=0) {
        setMainScene();
        Scene scene = new Scene(setMainScene(), 600, 600);
        Menu.window.setScene(scene);
        Menu.window.show();
        //} else {
        //registerFirstManager();
        //}
    }
}
