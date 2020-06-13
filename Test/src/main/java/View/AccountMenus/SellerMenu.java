package View.AccountMenus;

import Controller.AccountsManager.SellerAbilitiesManager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Product;
import Models.Request.AddOffRequest;
import Models.Request.AddProductRequest;
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

public class SellerMenu extends Menu {
    Product selectedProduct;

    public SellerMenu(Menu parentMenu) {
        super("Seller ", parentMenu);
    }

    @Override
    public void show() {
       /* if (RegisterSellerMenu.getCurrentSeller() != null) {
            //System.out.println("1.view personal info");
            //System.out.println("2.view company information");
           // System.out.println("3.view sales history");
            //System.out.println("4.manage products");
            //System.out.println("5.add product");
            System.out.println("6.remove product");
            //System.out.println("7.show categories");
            System.out.println("8.view offs");
            //System.out.println("9.view balance");
           // System.out.println("10.logout");
           // System.out.println("11.back");
        }*/
        setPersonalScene();
    }

    public void setPersonalScene() {
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
        Button viewSalesHistory = new Button("View sales history");
        viewSalesHistory.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSalesHistoryScene();
            }
        });

        Button manageProductButton = new Button("Manage products");
        manageProductButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        Button viewOffsButton = new Button("View offs");
        viewOffsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO
            }
        });
        Button showCategories = new Button("Show category");
        showCategories.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setShowCategoriesScene();
            }
        });

        Button editButton = new Button("Edit field");
        editButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEditScene();
            }
        });
        vBox.getChildren().addAll(backButton, viewSalesHistory, manageProductButton, viewOffsButton, editButton);
        pane.setLeft(vBox);

        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.CENTER);
        Label title = new Label("SELLER");
        Label username = new Label("username: " + RegisterSellerMenu.getCurrentSeller().getUserName());
        Label firstName = new Label("first name: " + RegisterSellerMenu.getCurrentSeller().getFirstName());
        Label lastName = new Label("last name: " + RegisterSellerMenu.getCurrentSeller().getLastName());
        Label email = new Label("email: " + RegisterSellerMenu.getCurrentSeller().getEmail());
        Label phoneNumber = new Label("phone number: " + RegisterSellerMenu.getCurrentSeller().getPhoneNumber());
        Label credit = new Label("credit: " + RegisterSellerMenu.getCurrentSeller().getCredit());
        Label companyName = new Label("company name: " + RegisterSellerMenu.getCurrentSeller());
        vBox1.getChildren().addAll(title, username, firstName, lastName, email, phoneNumber, credit, companyName);
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
                SellerAbilitiesManager.changeField(RegisterSellerMenu.getCurrentSeller(), field.getValue(), newContent.getText());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("successfully changed");
            }
        });
        vBox1.getChildren().addAll(hBox, notify);
        pane.setCenter(vBox1);
        Scene scene = new Scene(pane, 400, 200);
        Menu.window.setScene(scene);
    }

    public void setSalesHistoryScene() {
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
        listView.getItems().addAll(SellerAbilitiesManager.viewSalesHistory(RegisterSellerMenu.getCurrentSeller()));
        pane.setCenter(listView);
        Scene scene = new Scene(pane, 350, 350);
        Menu.window.setScene(scene);
    }

    public void setManageProductScene() {
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
        listView.getItems().addAll(SellerAbilitiesManager.showProducts(RegisterSellerMenu.getCurrentSeller()));
        pane.setCenter(listView);

        VBox vBox1 = new VBox(10);
        Button addProduct = new Button("Add product");
        Button editProduct = new Button("Edit product");
        Button removeButton = new Button("Remove product");
        addProduct.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setAddProductScene();
            }
        });
        editProduct.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedProduct = Product.getProductByName(listView.getSelectionModel().getSelectedItem());
                setEditProductScene();
            }
        });
        removeButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        vBox1.getChildren().addAll(addProduct, editProduct, removeButton);
    }

    public void setEditProductScene() {
        BorderPane pane = new BorderPane();
        Label notify = new Label();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setManageProductScene();
            }
        });
        vBox.getChildren().addAll(back);
        pane.setTop(back);

        HBox hBox = new HBox(10);
        ChoiceBox<String> field = new ChoiceBox<>();
        field.getItems().addAll("name", "company name", "description", "seller", "price", "category");
        TextField newContent = new TextField();
        newContent.setPromptText("new content for this field");
        Button button = new Button("change");
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SellerAbilitiesManager.sendEditingProductRequest(selectedProduct, RegisterSellerMenu.getCurrentSeller(), field.getValue(), newContent.getText());
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("request sent");
            }
        });
        hBox.getChildren().addAll(field, newContent, button, notify);
        pane.setCenter(hBox);
        Scene scene = new Scene(pane, 350, 350);
        Menu.window.setScene(scene);
    }

    public void setAddProductScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setManageProductScene();
            }
        });
        vBox.getChildren().addAll(back);
        pane.setTop(back);
        VBox vBox1 = new VBox(10);
        Label notify = new Label();
        TextField ID = new TextField();
        ID.setPromptText("product ID");
        TextField name = new TextField();
        name.setPromptText("name");
        TextField company = new TextField();
        company.setPromptText("company");
        TextField price = new TextField();
        price.setPromptText("price");
        TextField category = new TextField("category");
        category.setPromptText("category");
        TextField explanation = new TextField();
        explanation.setPromptText("explanation");
        TextField feature = new TextField();
        feature.setPromptText("feature");
        Button add = new Button("add");
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Product product = SellerAbilitiesManager.addProduct(ID.getText(), name.getText(), company.getText(), Double.parseDouble(price.getText()),
                        Category.getCategoryByName(category.getText()), RegisterSellerMenu.getCurrentSeller(), explanation.getText(), feature.getText());
                new AddProductRequest(RegisterSellerMenu.getCurrentSeller(), RegisterManagerMenu.getCurrentManager(), product, Category.getCategoryByName(category.getText()));
                notify.setStyle("-fx-text-fill: #3193ff");
                notify.setText("request sent to manager");
            }
        });
        vBox1.getChildren().addAll(ID, name, company, price, category, explanation, feature);
        pane.setCenter(vBox1);
        Scene scene = new Scene(pane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void removeProduct() {
       /* String command;
        Seller seller = RegisterSellerMenu.getCurrentSeller();
        while (true) {
            command = scanner.nextLine();
            Pattern removeProductPattern = Pattern.compile("remove product\\s(.+)");
            Matcher removeProductMatcher = removeProductPattern.matcher(command);
            if (command.matches("remove product\\s(.+)")) {
                removeProductMatcher.find();
                try {
                    SellerAbilitiesManager.removeProduct(seller, removeProductMatcher.group(1));
                    System.out.println("product removed successfully");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("remove product [productID]");
                System.out.println("back");
            }
        }*/
    }

    public void setShowCategoriesScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button button = new Button("Back");
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
        vBox.getChildren().addAll(button);
        pane.setTop(vBox);
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(SellerAbilitiesManager.showCategories());
        pane.setCenter(listView);
        Scene scene = new Scene(pane, 350, 350);
        Menu.window.setScene(scene);
    }

    public void viewOffs() {
        /*String command;
        Seller seller = RegisterSellerMenu.getCurrentSeller();
        System.out.println(SellerAbilitiesManager.viewOffs(seller));
        while (true) {
            command = scanner.nextLine();
            Pattern viewOffPattern = Pattern.compile("view\\s(.+)");
            Matcher viewOffMatcher = viewOffPattern.matcher(command);
            Pattern editOffPattern = Pattern.compile("edit\\s(.+)");
            Matcher editOffMatcher = editOffPattern.matcher(command);
            if (command.matches("view\\s(.+)")) {
                viewOffMatcher.find();
                try {
                    SellerAbilitiesManager.isThereOffByThisId(seller, viewOffMatcher.group(1));
                    System.out.println(SellerAbilitiesManager.viewOffByGettingId(seller, viewOffMatcher.group(1)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("edit\\s(.+)")) {
                editOffMatcher.find();
                try {
                    SellerAbilitiesManager.isThereOffByThisId(seller, editOffMatcher.group(1));
                    System.out.println("enter the field you want to change: ");
                    String field = scanner.nextLine();
                    System.out.println("enter the content for this field: ");
                    String newContent = scanner.nextLine();
                    SellerAbilitiesManager.sendEditingOffRequest(editOffMatcher.group(1), seller, field, newContent);
                    System.out.println("request sent to manager");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("add off")) {
                ArrayList<String> productsName = new ArrayList<>();
                System.out.println("enter discount's id");
                String id = scanner.nextLine();
                System.out.println("enter discount's beginningDate(yyyy-mm-dd)");
                String beginningDate = scanner.nextLine();
                System.out.println("enter discount's endingDate(yyyy-mm-dd)");
                String endingDate = scanner.nextLine();
                System.out.println("enter discount's percent");
                String discountPercent = scanner.nextLine();
                System.out.println("how many products have this discount:");
                int num = Integer.valueOf(scanner.nextLine());
                for (int i = 0; i < num; i++) {
                    System.out.println("enter products name: ");
                    productsName.add(scanner.nextLine());
                }
                Discount discount = SellerAbilitiesManager.addDiscount(id, beginningDate, endingDate,
                        Double.parseDouble(discountPercent), productsName);
                new AddOffRequest(seller, RegisterManagerMenu.getCurrentManager(), discount);
                System.out.println("request sent to manager");
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("view [off id]");
                System.out.println("edit [off id]");
                System.out.println("add off");
                System.out.println("back");
            }
        }*/
    }


}