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
        Button backButton = new Button("Back");
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
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
        viewOrdersButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setViewOrdersScene();
            }
        });
        Button viewListOfDiscountCodes = new Button("Discount codes");
        viewListOfDiscountCodes.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setDiscountCodeScene();
            }
        });
        Button cartButton = new Button("View cart");
        cartButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleCartPage();
            }
        });
        Button editButton = new Button("Edit field");
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
        Label title = new Label("CUSTOMER");
        Label username = new Label("username: " + RegisterCustomerMenu.getCurrentCustomer().getUserName());
        Label firstName = new Label("first name: " + RegisterCustomerMenu.getCurrentCustomer().getFirstName());
        Label lastName = new Label("last name: " + RegisterCustomerMenu.getCurrentCustomer().getLastName());
        Label email = new Label("email: " + RegisterCustomerMenu.getCurrentCustomer().getEmail());
        Label phoneNumber = new Label("phone number: " + RegisterCustomerMenu.getCurrentCustomer().getPhoneNumber());
        Label credit = new Label("credit: " + RegisterCustomerMenu.getCurrentCustomer().getCredit());
        vBox1.getChildren().addAll(title, username, firstName, lastName, email, phoneNumber, credit);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }
    public void handleCartPage(){
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

    public void setEditScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(5);
        Label notify = new Label();
        Button backButton = new Button("Back");
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
        field.getItems().addAll("first name", "last name", "password", "email", "phone number");
        TextField newContent = new TextField();
        newContent.setPromptText("new content for this field");
        Button button = new Button("change");
        hBox.getChildren().addAll(field, newContent, button);
        VBox vBox1 = new VBox(5);
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
        Scene scene = new Scene(pane, 400, 200);
        Menu.window.setScene(scene);
    }


    public void setViewOrdersScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
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
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
        //TODO rate product / asan nemidonam chejori
    }

    public void setDiscountCodeScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
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
                //TODO mitonim alert type ro information bezarim
                alert.setTitle("show discount code");
                alert.setHeaderText("discount code information");
                String s = CustomerAbilitiesManager.showDiscountCodeInfo(listView.getSelectionModel().getSelectedItem());
                alert.setContentText(s);
                alert.show();
            }
        });
        pane.setCenter(listView);
        Scene scene = new Scene(pane, 350, 350);
        Menu.window.setScene(scene);
    }
}