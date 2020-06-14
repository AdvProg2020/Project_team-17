package View.AccountMenus;

import Controller.AccountsManager.ManagerAbilitiesManager;
import Controller.RegisterAndLoginManager;
import Models.Accounts.Manager;
import Models.Category;
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

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu {
    Category selectedCategory;

    public ManagerMenu(Menu parentMenu) {
        super("Manager ", parentMenu);
    }

    @Override
    public void show() {
       /* if (RegisterManagerMenu.getCurrentManager() != null) {
            System.out.println("1.view personal info");
            System.out.println("2.manage users");
            System.out.println("3.manage all products");
            System.out.println("4.create discount code");
            System.out.println("5.view discount codes");
            System.out.println("6.manage requests");
            System.out.println("7.manage categories");
            System.out.println("8.logout");
            System.out.println("9.back");
        } */
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
        Button manageUsers = new Button("Manage users");
        manageUsers.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setManageUsersScene();
            }
        });
        Button manageProducts = new Button("Manage products");
        manageProducts.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        Button manageDiscountCodes = new Button("Manage discount codes");
        manageDiscountCodes.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        Button manageRequests = new Button("Manage requests");
        manageRequests.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        Button manageCategories = new Button("Manage categories");
        manageCategories.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });

        Button editButton = new Button("Edit field");
        editButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEditScene();
            }
        });
        vBox.getChildren().addAll(backButton, manageUsers, manageProducts, manageDiscountCodes, manageDiscountCodes, manageCategories, editButton);

        pane.setLeft(vBox);
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        Label title = new Label("MANAGER");
        Label username = new Label("username: " + RegisterManagerMenu.getCurrentManager().getUserName());
        Label firstName = new Label("first name: " + RegisterManagerMenu.getCurrentManager().getFirstName());
        Label lastName = new Label("last name: " + RegisterManagerMenu.getCurrentManager().getLastName());
        Label email = new Label("email: " + RegisterManagerMenu.getCurrentManager().getEmail());
        Label phoneNumber = new Label("phone number: " + RegisterManagerMenu.getCurrentManager().getPhoneNumber());
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
                //TODO
            }
        });
        discountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        logoutButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleLogout();
            }
        });
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
                ManagerAbilitiesManager.changeField(RegisterManagerMenu.getCurrentManager(), field.getValue(), newContent.getText());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("successfully changed");
            }
        });
        vBox1.getChildren().addAll(hBox, notify);
        pane.setCenter(vBox1);
        Scene scene = new Scene(pane, 400, 200);
        Menu.window.setScene(scene);
    }

    public void setManageUsersScene() {
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
        listView.getItems().addAll(ManagerAbilitiesManager.showAllAccounts());
        pane.setCenter(listView);

        VBox vBox1 = new VBox(10);
        Label notify = new Label();
        Button view = new Button("View account");
        Button delete = new Button("Delete account");
        Button addManager = new Button("Add manager");

        view.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.NONE);
                //TODO mitonim alert type ro information bezarim
                alert.setTitle("show user info");
                alert.setHeaderText("user information");
                String s = ManagerAbilitiesManager.viewAccountByUsername(listView.getSelectionModel().getSelectedItem());
                alert.setContentText(s);
                alert.show();
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
            }
        });
        vBox1.getChildren().addAll(view, delete, addManager);
    }

    public void setAddManagerScene() {
        BorderPane pane = new BorderPane();
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
                setManageUsersScene();
            }
        });

        vBox.getChildren().addAll(backButton, title, userNameTextField, passwordField, firstNameTextField, lastNameTextField, emailTextField, phoneNumberTextField, addressTextField, SUButton, notify);
        vBox.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#abbaab , #ffffff)");
        pane.setCenter(vBox);
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    //check
    public void manageAllProducts() {
        //TODO ask to doce phase2 asan hamchin chizi nagofte chi kar konim?
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern removeProductPattern = Pattern.compile("remove product\\s(.+)");
            Matcher removeProductMatcher = removeProductPattern.matcher(command);
            if (command.matches("remove product\\s(.+)")) {
                removeProductMatcher.find();
                try {
                    ManagerAbilitiesManager.removeProduct(removeProductMatcher.group(1));
                    System.out.println("Product removed successfully");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("remove product [productID]");
                System.out.println("back");
            } else {
                System.out.println("invalid command");
            }
        }
    }

    //check
    public void createDiscountCode() {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.equals("create discount code")) {
                try {
                    getInfoForCreatingDiscountCode();
                } catch (Exception e) {
                    System.out.println("enter validate input(check customers have been registered and be sure the type of date is correct)");
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("create discount code");
                System.out.println("back");
            } else {
                System.out.println("invalid command");
            }
        }
    }

    //check
    public void viewDiscountCodes() {
        String command;
        System.out.println(ManagerAbilitiesManager.viewDiscountCodes());
        while (true) {
            command = scanner.nextLine();
            Pattern viewDiscountCodePattern = Pattern.compile("view discount code\\s(.+)");
            Matcher viewDiscountCodeMatcher = viewDiscountCodePattern.matcher(command);
            Pattern editDiscountCodePattern = Pattern.compile("edit discount code\\s(.+)");
            Matcher editDiscountCodeMatcher = editDiscountCodePattern.matcher(command);
            Pattern removeDiscountCodePattern = Pattern.compile("remove discount code\\s(.+)");
            Matcher removeDiscountCodeMatcher = removeDiscountCodePattern.matcher(command);
            if (command.matches("view discount code\\s(.+)")) {
                viewDiscountCodeMatcher.find();
                try {
                    ManagerAbilitiesManager.isThereDiscountCode(viewDiscountCodeMatcher.group(1));
                    System.out.println(ManagerAbilitiesManager.viewDiscountCode(viewDiscountCodeMatcher.group(1)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("edit discount code\\s(.+)")) {
                editDiscountCodeMatcher.find();
                try {
                    ManagerAbilitiesManager.isThereDiscountCode(editDiscountCodeMatcher.group(1));
                    System.out.println("enter the field you want to edit: ");
                    String field = scanner.nextLine();
                    System.out.println("enter new content for this field: ");
                    String newContent = scanner.nextLine();
                    System.out.println(ManagerAbilitiesManager.editDiscountCode(editDiscountCodeMatcher.group(1), field, newContent));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("remove discount code\\s(.+)")) {
                removeDiscountCodeMatcher.find();
                try {
                    ManagerAbilitiesManager.isThereDiscountCode(removeDiscountCodeMatcher.group(1));
                    ManagerAbilitiesManager.removeDiscountCode(removeDiscountCodeMatcher.group(1));
                    System.out.println("discount code removed successfully");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("view discount code [code]");
                System.out.println("edit discount code [code]");
                System.out.println("remove discount code [code]");
                System.out.println("back");
            } else {
                System.out.println("invalid command");
            }

        }
    }

    //check
    public void manageRequests() {
        String command;
        System.out.println(ManagerAbilitiesManager.showAllRequests());
        while (true) {
            command = scanner.nextLine();
            Pattern detailsOfRequestPattern = Pattern.compile("details\\s(.+)");
            Matcher detailsOfRequestMatcher = detailsOfRequestPattern.matcher(command);
            Pattern acceptRequestPattern = Pattern.compile("accept\\s(.+)");
            Matcher acceptRequestMatcher = acceptRequestPattern.matcher(command);
            Pattern declineRequestPattern = Pattern.compile("decline\\s(.+)");
            Matcher declineRequestMatcher = declineRequestPattern.matcher(command);
            if (command.matches("details\\s(.+)")) {
                detailsOfRequestMatcher.find();
                try {
                    System.out.println(ManagerAbilitiesManager.showDetailsOfRequest(detailsOfRequestMatcher.group(1)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("accept\\s(.+)")) {
                acceptRequestMatcher.find();
                try {
                    System.out.println(ManagerAbilitiesManager.acceptRequest(acceptRequestMatcher.group(1)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("decline\\s(.+)")) {
                declineRequestMatcher.find();
                try {
                    ManagerAbilitiesManager.declineRequest(declineRequestMatcher.group(1));
                    System.out.println("request declined");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("details [requestID]");
                System.out.println("accept [requestID]");
                System.out.println("decline [requestID]");
            } else if (command.equals("back")) {
                break;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void setCategoriesScene() {
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
        listView.getItems().addAll(ManagerAbilitiesManager.showAllCategories());
        pane.setCenter(listView);

        VBox vBox1 = new VBox(10);
        Label notify = new Label();
        Button edit = new Button("edit category");
        Button add = new Button("add category");
        Button remove = new Button("remove category");
        edit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedCategory= Category.getCategoryByName(listView.getSelectionModel().getSelectedItem());
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

    }

    public void addCategoryScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setCategoriesScene();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        HBox hBox = new HBox();
        Label notify = new Label();
        TextField name = new TextField();
        name.setPromptText("category name");
        TextField feature = new TextField();
        feature.setPromptText("category feature");
        Button add= new Button("Add category");
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ManagerAbilitiesManager.addCategory(name.getText() , feature.getText());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("category successfully added");
            }
        });
        hBox.getChildren().addAll(name,feature,add,notify);
        pane.setCenter(hBox);
        Scene scene = new Scene(pane,350,350);
        Menu.window.setScene(scene);
    }

    public void setEditCategoryScene(){
        BorderPane pane = new BorderPane();
        HBox hBox = new HBox(10);
        Label notify = new Label();
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll("feature", "name");
        TextField newContent = new TextField();
        newContent.setPromptText("new content for this field");
        Button button = new Button("change");
        hBox.getChildren().addAll(field, newContent, button);
        VBox vBox1 = new VBox(5);
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
        Scene scene = new Scene(pane, 350 , 350);
        Menu.window.setScene(scene);
    }


    //check
    private void getInfoForCreatingDiscountCode() {
        String code = "";
        ArrayList<String> customersName = new ArrayList<>();
        try {
            System.out.println("Enter code: ");
            code = scanner.nextLine();
            System.out.println("Enter beginning date(yyyy-mm-dd): ");
            String startDate = scanner.nextLine();
            System.out.println("Enter ending date(yyyy-mm-dd): ");
            String endDate = scanner.nextLine();
            System.out.println("Enter discount percent: ");
            String discountPercent = scanner.nextLine();
            System.out.println("Enter maximum discount amount :");
            String maxAmountForDiscount = scanner.nextLine();
            System.out.println("Enter number of repeat for this discount code: ");
            String repeat = scanner.nextLine();

            System.out.println("how many customer have this code: ");
            int numOfCustomer = Integer.valueOf(scanner.nextLine());
            for (int i = 0; i < numOfCustomer; i++) {
                System.out.println("enter the name of customer:");
                String name = scanner.nextLine();
                customersName.add(name);
            }
            ManagerAbilitiesManager.createDiscountCode(code, startDate, endDate, discountPercent, maxAmountForDiscount, Integer.parseInt(repeat), customersName);
        } catch (Exception e) {
            ManagerAbilitiesManager.removeDiscountCodeDueToException(code, customersName);
        }
    }


}
