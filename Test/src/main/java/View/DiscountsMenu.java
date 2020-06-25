        package View;

import Controller.DiscountManager;
import Controller.ProductsManager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.util.ArrayList;

public class DiscountsMenu extends Menu {
    private ArrayList<String> currentFilters = new ArrayList<>();

    public DiscountsMenu(Menu parentMenu) {
        super("Discounts Menu", parentMenu);
    }

    @Override
    public void show() {
        setDiscountScene();
    }

    public void setDiscountScene() {
        ScrollPane scrollPane = new ScrollPane();
        Button backButton = new Button("Back");
        Label notify = new Label();
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productButton = new Button("Products");
        Button logoutButton = new Button("Logout");
        addActionForMainButtons(accountsButton, productButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productButton, logoutButton);
        HBox bar = new HBox(30);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
                notify.setText("");
            }
        });
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableList(filterChoiceBoxItems()));
        TextField textField = new TextField();
        textField.setPromptText("filter by ...");
        Button submit = new Button("submit filter");
        submit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String filter = (String) choiceBox.getValue();
                    String name = textField.getText();
                    if (filter != null) {
                        if (filter.equals("category")) {
                            Category category = Category.getCategoryByName(textField.getText());
                            if (category != null) {
                                filter(filter, name);
                                setFilterScene();
                            } else {
                                notify.setText("invalid category name");
                                notify.setStyle("-fx-text-fill: #ff4f59");
                            }
                        } else if (filter.equals("seller")) {
                            Seller seller = Seller.getSellerByName(textField.getText());
                            if (seller != null) {
                                filter(filter, name);
                                setFilterScene();
                            } else {
                                notify.setText("invalid seller name");
                                notify.setStyle("-fx-text-fill: #ff4f59");
                            }
                        } else {
                            filter(filter, name);
                            setFilterScene();
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        });
        HBox filter = new HBox(10);
        filter.getChildren().addAll(choiceBox, textField, submit);
        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableList(sortChoiceBoxItems()));
        Button submitButton = new Button("submit sort");
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    ProductsManager.sort((String) choiceBox1.getValue());
                    setSortScene();
                } catch (Exception ignored) {
                }
            }
        });
        HBox sort = new HBox(5);
        sort.getChildren().addAll(choiceBox1, submitButton);
        bar.getChildren().addAll(backButton, mainButtons, filter, sort);
        ArrayList<HBox> hBoxes = new ArrayList<>();
        hBoxes.add(bar);
        for (Product product : DiscountManager.showProducts()) {
            HBox hBox = new HBox(10);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception ignored) {
            }
            ImageView imageView = new ImageView(image);
            imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    handleProductPage(product);
                }
            });
            Label name = new Label(product.getName());
            Label price = new Label(product.getPrice() + "$");
            Label score = new Label("score: " + product.getPrice());
            hBox.getChildren().addAll(imageView, name, price, score);
            hBoxes.add(hBox);
        }
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(hBoxes);
        scrollPane.setContent(vBox);
        Scene scene = new Scene(scrollPane, 1270, 650);
        Menu.window.setScene(scene);
    }

    public void setFilterScene() {
        ScrollPane scrollPane = new ScrollPane();
        Label notify = new Label();
        Button backButton = new Button("Back");
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productsButton = new Button("Discounts");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        addActionForMainButtonsForFilterAndSort(accountsButton, discountButton, productsButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productsButton, discountButton, logoutButton);
        HBox bar = new HBox(30);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
                notify.setText("");
            }
        });
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableList(filterChoiceBoxItems()));
        TextField textField = new TextField();
        textField.setPromptText("filter by ...");
        Button submit = new Button("submit");
        submit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String filter = (String) choiceBox.getValue();
                    String name = textField.getText();
                    if (filter != null) {
                        if (filter.equals("category")) {
                            Category category = Category.getCategoryByName(textField.getText());
                            if (category != null) {
                                filter(filter, name);
                                setFilterScene();
                            } else {
                                notify.setText("invalid category name");
                                notify.setStyle("-fx-text-fill: #ff4f59");
                            }
                        } else if (filter.equals("seller")) {
                            Seller seller = Seller.getSellerByName(textField.getText());
                            if (seller != null) {
                                filter(filter, name);
                                setFilterScene();
                            } else {
                                notify.setText("invalid seller name");
                                notify.setStyle("-fx-text-fill: #ff4f59");
                            }
                        } else {
                            filter(filter, name);
                            setFilterScene();
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        });
        HBox filter = new HBox(10);
        filter.getChildren().addAll(choiceBox, textField, submit);
        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableList(sortChoiceBoxItems()));
        Button submitButton = new Button("submit sort");
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    ProductsManager.sort((String) choiceBox1.getValue());
                    setSortScene();
                } catch (Exception ignored) {
                }
            }
        });
        HBox sort = new HBox(5);
        sort.getChildren().addAll(choiceBox1, submitButton);

        bar.getChildren().addAll(backButton, mainButtons, filter, sort);
        ArrayList<HBox> hBoxes = new ArrayList<>();
        hBoxes.add(bar);
        for (Product product : DiscountManager.getFilterProduct()) {
            HBox hBox = new HBox(10);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception ignored) {
            }
            ImageView imageView = new ImageView(image);
            imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    handleProductPage(product);
                }
            });
            Label name = new Label(product.getName());
            Label price = new Label(product.getPrice() + "$");
            Label score = new Label("score: " + product.getPrice());
            hBox.getChildren().addAll(imageView, name, price, score);
            hBoxes.add(hBox);
        }
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(hBoxes);
        scrollPane.setContent(vBox);
        Scene scene = new Scene(scrollPane, 1270, 650);
        Menu.window.setScene(scene);
    }

    public void setSortScene() {
        ScrollPane scrollPane = new ScrollPane();
        Label notify = new Label();
        Button backButton = new Button("Back");
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productsButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        addActionForMainButtonsForFilterAndSort(accountsButton, discountButton, productsButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productsButton, discountButton, logoutButton);
        HBox bar = new HBox(30);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
                notify.setText("");
            }
        });
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableList(filterChoiceBoxItems()));
        TextField textField = new TextField();
        textField.setPromptText("filter by ...");
        Button submit = new Button("submit");
        submit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String filter = (String) choiceBox.getValue();
                    String name = textField.getText();
                    if (filter != null) {
                        if (filter.equals("category")) {
                            Category category = Category.getCategoryByName(textField.getText());
                            if (category != null) {
                                filter(filter, name);
                                setFilterScene();
                            } else {
                                notify.setText("invalid category name");
                                notify.setStyle("-fx-text-fill: #ff4f59");
                            }
                        } else if (filter.equals("seller")) {
                            Seller seller = Seller.getSellerByName(textField.getText());
                            if (seller != null) {
                                filter(filter, name);
                                setFilterScene();
                            } else {
                                notify.setText("invalid seller name");
                                notify.setStyle("-fx-text-fill: #ff4f59");
                            }
                        } else {
                            filter(filter, name);
                            setFilterScene();

                        }
                    }
                } catch (Exception ignored) {
                }
            }
        });
        HBox filter = new HBox(10);
        filter.getChildren().addAll(choiceBox, textField, submit);
        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableList(sortChoiceBoxItems()));
        Button submitButton = new Button("submit sort");
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    ProductsManager.sort((String) choiceBox1.getValue());
                    setSortScene();
                }catch (Exception ignored){

                }
            }
        });
        HBox sort = new HBox(5);
        sort.getChildren().addAll(choiceBox1, submitButton);

        bar.getChildren().addAll(backButton, mainButtons, filter, sort);
        ArrayList<HBox> hBoxes = new ArrayList<>();
        hBoxes.add(bar);
        for (Product product : DiscountManager.getSortProducts()) {
            HBox hBox = new HBox(10);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception ignored) {
            }
            ImageView imageView = new ImageView(image);
            imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    handleProductPage(product);
                }
            });
            Label name = new Label(product.getName());
            Label price = new Label(product.getPrice() + "$");
            Label score = new Label("score: " + product.getPrice());
            hBox.getChildren().addAll(imageView, name, price, score);
            hBoxes.add(hBox);
        }
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(hBoxes);
        scrollPane.setContent(vBox);
        Scene scene = new Scene(scrollPane, 1270, 650);
        Menu.window.setScene(scene);
    }

    public void addActionForMainButtonsForFilterAndSort(Button accountsButton, Button productsButton, Button discountButton, Button logoutButton) {
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

    public void handleProductPage(Product product) {
        ProductMenu productMenu = new ProductMenu(this, product);
        productMenu.show();
    }

    Nona Ghazizade, [25.06.20 16:54]
    public void addActionForMainButtons(Button accountsButton, Button productsButton, Button logoutButton) {
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

    private ArrayList<String> filterChoiceBoxItems() {
        ArrayList<String> choiceBoxItems = new ArrayList<>();
        choiceBoxItems.add("name");
        choiceBoxItems.add("category");
        choiceBoxItems.add("price");
        choiceBoxItems.add("seller");
        choiceBoxItems.add("availability");
        choiceBoxItems.add("company name");
        choiceBoxItems.add("special feature");
        return choiceBoxItems;
    }

    private ArrayList<String> sortChoiceBoxItems() {
        ArrayList<String> choiceBoxItems = new ArrayList<>();
        choiceBoxItems.add("price");
        choiceBoxItems.add("score");
        return choiceBoxItems;
    }


    private void addFilterToCurrentFilter(String name) {
        for (String filter : currentFilters) {
            if (!(filter.contains(name))) {
                currentFilters.add(name);
            }
        }
    }

    private void filterByName(String name) {
        DiscountManager.filterByName(name);
        addFilterToCurrentFilter("name");
    }

    private void filterByCategory(String name) {
        DiscountManager.filterByCategory(name);
        addFilterToCurrentFilter("category");
    }

    private void filterByPrice(String name) {
        String[] split = name.split("\\s");
        DiscountManager.filterByPrice(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
        addFilterToCurrentFilter("price");
    }

    private void filterBySeller(String name) {
        DiscountManager.filterBySeller(name);
        addFilterToCurrentFilter("seller");
    }

    private void filterByAvailability(String name) {
        DiscountManager.filterByAvailability(ProductEnum.valueOf(name));
        addFilterToCurrentFilter("availability");
    }

    private void filterBySpecialFeature(String name) {
        DiscountManager.filterBySpecialFeature(name);
        addFilterToCurrentFilter("special feature");
    }

    private void filterByBrand(String name) {
        DiscountManager.filterByCompanyName(name);
        addFilterToCurrentFilter("brand");
    }

    Nona Ghazizade, [25.06.20 16:54]
    private void filter(String filter, String name) {
        if (filter.equals("name")) {
            filterByName(name);
        } else if (filter.equals("category")) {
            filterByCategory(name);
        } else if (filter.equals("price")) {
            filterByPrice(name);
        } else if (filter.equals("seller")) {
            filterBySeller(name);
        } else if (filter.equals("availability")) {
            filterByAvailability(name);
        } else if (filter.equals("special features")) {
            filterBySpecialFeature(name);
        } else if (filter.equals("brand")) {
            filterByBrand(name);
        }
    }


//    private void removeFilterFromCurrentFilter(String name) {
//        for (String filter : currentFilters) {
//            if (filter.contains(name)) {
//                currentFilters.remove(name);
//            }
//        }
//    }

//    private void disableFilterByName() {
//        System.out.println("enter a name");
//        String name = scanner.nextLine();
//        DiscountManager.disableFilterByName(name);
//        removeFilterFromCurrentFilter("name");
//    }
//
//    private void disableFilterByCategory() {
//        System.out.println("enter a category name: ");
//        String categoryName = scanner.nextLine();
//        DiscountManager.disableFilterByCategory(categoryName);
//        removeFilterFromCurrentFilter("category");
//    }
//
//    private void disableFilterByPrice() {
//        System.out.println("enter minimum price: ");
//        double min = scanner.nextDouble();
//        System.out.println("enter maximum price: ");
//        double max = scanner.nextDouble();
//        DiscountManager.disableFilterByPrice(min, max);
//        removeFilterFromCurrentFilter("price");
//    }
//
//    private void disableFilterBySeller() {
//        System.out.println("enter seller name: ");
//        String name = scanner.nextLine();
//        DiscountManager.disableFilterBySeller(name);
//        removeFilterFromCurrentFilter("seller");
//    }
//
//    private void disableFilterByAvailability() {
//        System.out.println("enter a state for availability");
//        String state = scanner.nextLine();
//        DiscountManager.disableFilterByAvailability(ProductEnum.valueOf(state));
//        removeFilterFromCurrentFilter("availability");
//    }
//
//    private void disableFilterBySpecialFeature() {
//        System.out.println("enter special feature: ");
//        String feature = scanner.nextLine();
//        DiscountManager.disableFilterBySpecialFeature(feature);
//        removeFilterFromCurrentFilter("special features");
//    }
//
//    private void disableFilterByBrand() {
//        System.out.println("enter company name: ");
//        String name = scanner.nextLine();
//        DiscountManager.disableFilterByCompanyName(name);
//        removeFilterFromCurrentFilter("brand");
//    }

//    private void disableFilter(String command) {
//        if (command.equals("name")) {
//            disableFilterByName();
//        } else if (command.equals("category")) {
//            disableFilterByCategory();
//        } else if (command.equals("price")) {
//            disableFilterByPrice();
//        } else if (command.equals("seller")) {
//            disableFilterBySeller();
//        } else if (command.equals("availability")) {
//            disableFilterByAvailability();
//        } else if (command.equals("special features")) {
//            disableFilterBySpecialFeature();
//        } else if (command.equals("brand")) {
//            disableFilterByBrand();
//        }
//    }

//    public void filtering() {
//        String command;
//        DiscountManager.addAllProductsToFilterProducts();
//        while (true) {
//            command = scanner.nextLine();
//            Pattern filterPattern = Pattern.compile("filter\\s(.+)");
//            Matcher filterMatcher = filterPattern.matcher(command);
//            Pattern disableFilterPattern = Pattern.compile("disable filter\\s(.+)");
//            Matcher disableFilterMatcher = disableFilterPattern.matcher(command);
//            if (command.equals("show available filters")) {
//                System.out.println("available fil");
//            } else if (command.matches("filter\\s(.+)")) {
//                filterMatcher.find();
//                filter(filterMatcher.group(1));
//            } else if (command.equals("current filters")) {
//                System.out.println(currentFilters);
//            } else if (command.matches("disable filter\\s(.+)")) {
//                disableFilterMatcher.find();
//                disableFilter(disableFilterMatcher.group(1));
//            } else if (command.equals("back")) {
//                break;
//            } else if (command.equals("help")) {
//                System.out.println("commands that you can enter are:");
//                System.out.println("filter [an available filter]");
//                System.out.println("current filters");
//                System.out.println("disable filter [a selected filter]");
//                System.out.println("back");
//            }
//        }
//    }
//
//    public void sorting() {
//        String command;
//        while (true) {
//            command = scanner.nextLine();
//            Pattern sortPattern = Pattern.compile("sort\\s(.+)");
//            Matcher sortMatcher = sortPattern.matcher(command);
//            if (command.equals("show available sort")) {
//                System.out.println(DiscountManager.showAvailableSort());
//            } else if (command.matches("sort\\s(.+)")) {
//                sortMatcher.find();
//                DiscountManager.sort(sortMatcher.group(1));
//                System.out.println(DiscountManager.getSortProductsName(DiscountManager.getCurrentSort()));
//            } else if (command.equals("current sort")) {
//                System.out.println(DiscountManager.getCurrentSort());
//            } else if (command.matches("disable sort")) {
//                try {
//                    DiscountManager.disableSort(DiscountManager.getCurrentSort());
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            } else if (command.matches("back")) {
//                break;
//            } else if (command.matches("help")) {
//                System.out.println("commands that you can enter are:");
//                System.out.println("sort [an available sort]");
//                System.out.println("current sort");
//                System.out.println("disable sort");
//                System.out.println("back");
//            }
//        }
//
//    }

}