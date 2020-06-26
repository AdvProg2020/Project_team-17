package View.AccountMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import View.*;
import View.Menu;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class CustomerMenu extends Menu {
    public CustomerMenu(Menu parentMenu) {
        super("Customer ", parentMenu);
    }

    @Override
    public void show() {
        setPersonalInfoScene();
    }

    public void setPersonalInfoScene() {
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
        addActionForMainButtons(accountsButton, productButton, discountButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productButton, discountButton, logoutButton);
        pane.setTop(mainButtons);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
        VBox vBox = new VBox(10);
        Button viewOrdersButton = new Button("View orders");
        viewOrdersButton.setStyle(style);
        viewOrdersButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setViewOrdersScene();
            }
        });
        Button viewListOfDiscountCodes = new Button("Discount codes");
        viewListOfDiscountCodes.setStyle(style);
        viewListOfDiscountCodes.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setDiscountCodeScene();
            }
        });
        Button cartButton = new Button("View cart");
        cartButton.setStyle(style);
        cartButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleCartPage();
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
        vBox.getChildren().addAll(backButton, viewOrdersButton, cartButton, viewListOfDiscountCodes, editButton);
        pane.setLeft(vBox);
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        Text title = new Text("CUSTOMER");
        Text username = new Text("username: " + RegisterCustomerMenu.getCurrentCustomer().getUserName());
        Text firstName = new Text("first name: " + RegisterCustomerMenu.getCurrentCustomer().getFirstName());
        Text lastName = new Text("last name: " + RegisterCustomerMenu.getCurrentCustomer().getLastName());
        Text email = new Text("email: " + RegisterCustomerMenu.getCurrentCustomer().getEmail());
        Text phoneNumber = new Text("phone number: " + RegisterCustomerMenu.getCurrentCustomer().getPhoneNumber());
        Text credit = new Text("credit: " + RegisterCustomerMenu.getCurrentCustomer().getCredit());
        title.setFont(Font.loadFont("file:src/main/java/Fonts/FiraSans-Medium.otf", 34));
        username.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        firstName.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        lastName.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        email.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        phoneNumber.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        credit.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        vBox1.getChildren().addAll(title, username, firstName, lastName, email, phoneNumber, credit);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
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
                CustomerAbilitiesManager.changeField(RegisterCustomerMenu.getCurrentCustomer(), field.getValue(), newContent.getText());
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


    public void setViewOrdersScene() {
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
                show();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(CustomerAbilitiesManager.viewOrders(RegisterCustomerMenu.getCurrentCustomer()));
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("show order");
                alert.setHeaderText("buy log information");
                String s = CustomerAbilitiesManager.showOrder(listView.getSelectionModel().getSelectedItem());
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.setContentText(s);
                alert.show();
            }
        });
        pane.setCenter(listView);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setDiscountCodeScene() {
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
                show();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(CustomerAbilitiesManager.showDiscountCodes(RegisterCustomerMenu.getCurrentCustomer()));
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("show discount code");
                alert.setHeaderText("discount code information");
                String s = CustomerAbilitiesManager.showDiscountCodeInfo(listView.getSelectionModel().getSelectedItem());
                alert.setContentText(s);
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

    public void handleCartPage() {
        CartMenu cartMenu = new CartMenu(this);
        cartMenu.show();
    }

    public void addActionForMainButtons(Button accountsButton, Button productsButton, Button discountButton, Button logoutButton) {
        accountsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleAccountsMenu();
            }
        });
        productsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleProductsMenu();
            }
        });
        discountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleDiscountsMenu();
            }
        });
        logoutButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
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
            RegisterCustomerMenu.setCurrentCustomer(null);
        } else if (RegisterSellerMenu.getCurrentSeller() != null) {
            RegisterSellerMenu.setCurrentSeller(null);
        } else if (RegisterManagerMenu.getCurrentManager() != null) {
            RegisterManagerMenu.setCurrentManager(null);
        }
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}