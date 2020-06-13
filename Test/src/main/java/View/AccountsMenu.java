package View;

import View.AccountMenus.CustomerMenu;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.FileInputStream;

public class AccountsMenu extends Menu {
    public AccountsMenu(Menu parentMenu) {
        super("Accounts Menu", parentMenu);
    }

    @Override
    public void show() {
        BorderPane pane = new BorderPane();
        VBox accountButtons = new VBox(15);
        accountButtons.setAlignment(Pos.CENTER);
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#cdded5 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: #3193ff;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); -fx-font-size: 1.4em; -fx-padding: 8px;";
        Button sellerAccountsButton = new Button("Seller accounts");
        sellerAccountsButton.setStyle(style);
        Button customerAccountsButton = new Button("Customer accounts");
        customerAccountsButton.setStyle(style);
        Button mangerAccountsButton = new Button("Manger Accounts");
        mangerAccountsButton.setStyle(style);
        Button backButton = new Button("Back");
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
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
        accountButtons.setBackground(mainBackground);
        addActionForButtons(sellerAccountsButton, customerAccountsButton, mangerAccountsButton);
        accountButtons.getChildren().addAll(backButton,sellerAccountsButton, customerAccountsButton, mangerAccountsButton);
        pane.setCenter(accountButtons);
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void addActionForButtons(Button sellerButton, Button customerButton, Button managerButton) {
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
                    handleCustomerMenu();
                }
            }
        });
        managerButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (RegisterManagerMenu.getCurrentManager() == null) {
                    handleRegisterManager();
                } else {
                    //TODO view personal info
                }
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

    public void handleRegisterManager() {
        RegisterManagerMenu registerManagerMenu = new RegisterManagerMenu(this);
        registerManagerMenu.show();
    }
    public void handleCustomerMenu(){
        CustomerMenu customerMenu = new CustomerMenu(this);
        customerMenu.show();
    }
}