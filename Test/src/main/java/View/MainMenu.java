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
import java.io.FileNotFoundException;
import java.util.HashMap;

public class MainMenu extends Menu {


    public MainMenu() {
        super("Main Menu", null);
    }

    @Override
    public void setScene() {
        VBox mainButtons = new VBox(15);
        mainButtons.setAlignment(Pos.CENTER);
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#cdded5 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: #3193ff;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); -fx-font-size: 1.4em; -fx-padding: 8px;";
        Button sellerAccountsButton = new Button("Seller accounts");
        sellerAccountsButton.setStyle(style);
        Button customerAccountsButton = new Button("Customer accounts");
        customerAccountsButton.setStyle(style);
        Button mangerAccountsButton = new Button("Manger Accounts");
        mangerAccountsButton.setStyle(style);
        Button productsButton = new Button("Products");
        productsButton.setStyle(style);
        Button discountButton = new Button("Discounts");
        discountButton.setStyle(style);
        Button exitButton = new Button("Exit");
        exitButton.setStyle(style);
        Image mainImage = null;
        try {
            mainImage = new Image(new FileInputStream("C:\\Users\\UX434FL\\IdeaProjects\\17\\Test\\src\\main\\java\\Images\\MAIN_BACKGROUND.png"));

        } catch (Exception e) {

        }
        BackgroundImage mainBackgroundImage = new BackgroundImage(mainImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background mainBackground = new Background(mainBackgroundImage);
        addActionForButtons(sellerAccountsButton, customerAccountsButton, mangerAccountsButton, productsButton, discountButton, exitButton);
        mainButtons.getChildren().addAll(sellerAccountsButton, customerAccountsButton, mangerAccountsButton, productsButton, discountButton, exitButton);
        mainButtons.setBackground(mainBackground);
        mainPane.setCenter(mainButtons);
    }

    public void addActionForButtons(Button sellerButton, Button customerButton, Button managerButton, Button productButton, Button discountButton, Button exitButton) {
        sellerButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (RegisterSellerMenu.getCurrentSeller() == null) {
                    handleRegisterSeller();
                } else {
                    //TODO view personal info
                }
            }
        });
        customerButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (RegisterCustomerMenu.getCurrentCustomer() == null) {
                    handleRegisterCustomer();
                } else {
                    //TODO view personal info
                }
            }
        });
        managerButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(RegisterManagerMenu.getCurrentManager()== null){
                    handleRegisterManager();
                }else {
                    //TODO view personal info
                }
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

    public void handleRegisterSeller() {
        RegisterSellerMenu registerSellerMenu = new RegisterSellerMenu(this);
        registerSellerMenu.show();
    }

    public void handleRegisterCustomer() {
        RegisterCustomerMenu registerCustomerMenu = new RegisterCustomerMenu(this);
        registerCustomerMenu.show();
    }
    public void handleRegisterManager(){
        RegisterManagerMenu registerManagerMenu = new RegisterManagerMenu(this);
        registerManagerMenu.show();
    }

    public void registerFirstManager(){
        VBox vBox = new VBox(10);
        Button backButton = new Button("Back");
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
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });

        vBox.getChildren().addAll(backButton, title, userNameTextField, passwordField, firstNameTextField, lastNameTextField, emailTextField, phoneNumberTextField, addressTextField, SUButton, notify);
        super.mainPane.setCenter(vBox);
        super.mainPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#abbaab , #ffffff)");
        Scene scene = new Scene(super.mainPane, 600, 600);
        Menu.window.setScene(scene);
    }


    @Override
    public void show() {
        //  if (Manager.getAllManagers().size()!=0) {
        setScene();
        Scene scene = new Scene(mainPane, 600, 600);
        Menu.window.setScene(scene);
        Menu.window.show();
        //} else {
        //registerFirstManager();
        //}
    }
}
