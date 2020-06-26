package View.AccountMenus;

import Controller.AccountsManager.ManagerAbilitiesManager;
import Controller.RegisterAndLoginManager;
import Controller.WriteIntoFile;
import Models.Accounts.Customer;
import Models.Category;
import Models.DiscountCode;
import View.*;
import View.Menu;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

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

        Button editButton = new Button("Edit field");
        editButton.setStyle(style);
        editButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEditScene();
            }
        });
        vBox.getChildren().addAll(backButton, manageUsers, manageProducts, manageDiscountCodes, manageRequests, manageCategories, editButton);
        pane.setLeft(vBox);
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        Text title = new Text("MANAGER");
        Text username = new Text("username: " + RegisterManagerMenu.getCurrentManager().getUserName());
        Text firstName = new Text("first name: " + RegisterManagerMenu.getCurrentManager().getFirstName());
        Text lastName = new Text("last name: " + RegisterManagerMenu.getCurrentManager().getLastName());
        Text email = new Text("email: " + RegisterManagerMenu.getCurrentManager().getEmail());
        Text phoneNumber = new Text("phone number: " + RegisterManagerMenu.getCurrentManager().getPhoneNumber());
        title.setFont(Font.loadFont("file:src/main/java/Fonts/FiraSans-Medium.otf", 34));
        username.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 44));
        firstName.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        lastName.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        email.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        phoneNumber.setFont(Font.loadFont("file:src/main/java/Fonts/DroidSans.ttf", 28));
        vBox1.getChildren().addAll(title, username, firstName, lastName, email, phoneNumber);
        pane.setCenter(vBox1);
        pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#e0eafc , #cfdef3)");
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
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
                ManagerAbilitiesManager.changeField(RegisterManagerMenu.getCurrentManager(), field.getValue(), newContent.getText());
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

    public void setManageUsersScene() {
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
        view.setStyle(style);
        delete.setStyle(style);
        addManager.setStyle(style);

        view.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("show user info");
                alert.setHeaderText("user information");
                String s = ManagerAbilitiesManager.viewAccountByUsername(listView.getSelectionModel().getSelectedItem());
                alert.setContentText(s);
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.show();
                notify.setText("");
            }
        });
        delete.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ManagerAbilitiesManager.deleteUser(listView.getSelectionModel().getSelectedItem());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("user deleted successfully");
            }
        });
        addManager.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setAddManagerScene();
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
        SUButton.setStyle(style);

        SUButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (RegisterAndLoginManager.canHaveAccountWithThisUsername(userNameTextField.getText())) {
                    ManagerAbilitiesManager.createAnotherManager(userNameTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(),
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
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("discount code info");
                alert.setHeaderText("discount code information");
                String s = ManagerAbilitiesManager.viewDiscountCode(listView.getSelectionModel().getSelectedItem());
                alert.setContentText(s);
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.show();
            }
        });

        edit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedDiscountCode = DiscountCode.getDiscountCodeWithCode(listView.getSelectionModel().getSelectedItem());
                editDiscountCodeScene();
            }
        });
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addDiscountCodeScene();
            }
        });
        remove.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ManagerAbilitiesManager.removeDiscountCode(listView.getSelectionModel().getSelectedItem());
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

    public void editDiscountCodeScene() {
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
                ManagerAbilitiesManager.editDiscountCode(selectedDiscountCode, field.getValue(), newContent.getText());
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
                    ManagerAbilitiesManager.createDiscountCode(ID.getText(), startDate.getText(), endDate.getText(), discountPercent.getText(), max.getText(), Integer.parseInt(count.getText()), s);
                    try {
                        WriteIntoFile.writeDiscountCodesIntoFile();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
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
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("show user info");
                alert.setHeaderText("user information");
                String s = ManagerAbilitiesManager.showDetailsOfRequest(listView.getSelectionModel().getSelectedItem());
                alert.setContentText(s);
                ButtonType buttonType = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonType);
                alert.show();
            }
        });
        accept.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ManagerAbilitiesManager.acceptRequest(listView.getSelectionModel().getSelectedItem());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("request accepted");
            }
        });
        decline.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
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
                selectedCategory = Category.getCategoryByName(listView.getSelectionModel().getSelectedItem());
                setEditCategoryScene();
            }
        });
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addCategoryScene();
            }
        });
        remove.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
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
                ManagerAbilitiesManager.addCategory(name.getText(), feature.getText());
                try {
                    WriteIntoFile.writeCategoriesIntoFile();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
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
                ManagerAbilitiesManager.editCategory(selectedCategory, field.getValue(), newContent.getText());
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
                ManagerAbilitiesManager.removeProduct(listView.getSelectionModel().getSelectedItem());
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