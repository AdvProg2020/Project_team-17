package View;

import Controller.ProductsManager;
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

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.scene.layout.VBox;


public class ProductsMenu extends Menu {
    private ArrayList<String> currentFilters = new ArrayList<>();

    public ProductsMenu(Menu parentMenu) {
        super("Products", parentMenu);
    }

    @Override
    public void show() {
        setProductsScene();
    }

    public void setProductsScene() {
        ScrollPane scrollPane = new ScrollPane();
        Button backButton = new Button("Back");
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        addActionForMainButtons(accountsButton, discountButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, discountButton, logoutButton);
        HBox bar = new HBox(30);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableList(filterChoiceBoxItems()));
        TextField textField = new TextField();
        textField.setPromptText("filter by ...");
        Button submit = new Button("submit filter");
        submit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String filter = (String) choiceBox.getValue();
                String name = textField.getText();
                filter(filter, name);
                setFilterScene();
            }
        });
        HBox filter = new HBox(10);
        filter.getChildren().addAll(choiceBox, textField, submit);
        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableList(sortChoiceBoxItems()));
        Button submitButton = new Button("submit sort");
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ProductsManager.sort((String) choiceBox1.getValue());
                setSortScene();
            }
        });
        HBox sort = new HBox(5);
        sort.getChildren().addAll(choiceBox1, submitButton);

        ChoiceBox category = new ChoiceBox(ProductsManager.showCategory());
        bar.getChildren().addAll(backButton, mainButtons, filter, sort, category);
        ArrayList<HBox> hBoxes = new ArrayList<>();
        hBoxes.add(bar);
        for (Product product : ProductsManager.showProducts()) {
            HBox hBox = new HBox(10);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception e) {
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
        Scene scene = new Scene(scrollPane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void handleProductPage(Product product) {
        ProductMenu productMenu = new ProductMenu(this, product);
        productMenu.show();
    }


    public void addActionForMainButtons(Button accountsButton, Button discountButton, Button logoutButton) {
        accountsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleAccountsMenu();
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

    public void setFilterScene() {
        ScrollPane scrollPane = new ScrollPane();
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
            }
        });
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableList(filterChoiceBoxItems()));
        TextField textField = new TextField();
        textField.setPromptText("filter by ...");
        Button submit = new Button("submit");
        submit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String filter = (String) choiceBox.getValue();
                String name = textField.getText();
                filter(filter, name);
                setFilterScene();
            }
        });
        HBox filter = new HBox(10);
        filter.getChildren().addAll(choiceBox, textField, submit);
        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableList(sortChoiceBoxItems()));
        Button submitButton = new Button("submit sort");
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ProductsManager.sort((String) choiceBox1.getValue());
                setSortScene();
            }
        });
        HBox sort = new HBox(5);
        sort.getChildren().addAll(choiceBox1, submitButton);

        bar.getChildren().addAll(backButton, mainButtons, filter, sort);
        ArrayList<HBox> hBoxes = new ArrayList<>();
        hBoxes.add(bar);
        for (Product product : ProductsManager.getFilterProduct()) {
            HBox hBox = new HBox(10);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception e) {
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
        Scene scene = new Scene(scrollPane, 600, 600);
        Menu.window.setScene(scene);
    }

    public void setSortScene() {
        ScrollPane scrollPane = new ScrollPane();
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
            }
        });
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableList(filterChoiceBoxItems()));
        TextField textField = new TextField();
        textField.setPromptText("filter by ...");
        Button submit = new Button("submit");
        submit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String filter = (String) choiceBox.getValue();
                String name = textField.getText();
                filter(filter, name);
                setFilterScene();
            }
        });
        HBox filter = new HBox(10);
        filter.getChildren().addAll(choiceBox, textField, submit);
        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableList(sortChoiceBoxItems()));
        Button submitButton = new Button("submit sort");
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ProductsManager.sort((String) choiceBox1.getValue());
                setSortScene();
            }
        });
        HBox sort = new HBox(5);
        sort.getChildren().addAll(choiceBox1, submitButton);

        bar.getChildren().addAll(backButton, mainButtons, filter, sort);
        ArrayList<HBox> hBoxes = new ArrayList<>();
        hBoxes.add(bar);
        for (Product product : ProductsManager.getFilterProduct()) {
            HBox hBox = new HBox(10);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception e) {
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
        Scene scene = new Scene(scrollPane, 600, 600);
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


    private void addFilterToCurrentFilter(String name) {
        for (String filter : currentFilters) {
            if (!(filter.contains(name))) {
                currentFilters.add(name);
            }
        }
    }

    private void filterByName(String name) {
        ProductsManager.filterByName(name);
        addFilterToCurrentFilter("name");
    }

    private void filterByCategory(String name) {
        ProductsManager.filterByCategory(name);
        addFilterToCurrentFilter("category");
    }

    private void filterByPrice(String name) {
        String[] split = name.split("\\s");
        ProductsManager.filterByPrice(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
        addFilterToCurrentFilter("price");
    }

    private void filterBySeller(String name) {
        ProductsManager.filterBySeller(name);
        addFilterToCurrentFilter("seller");
    }

    private void filterByAvailability(String name) {
        ProductsManager.filterByAvailability(ProductEnum.valueOf(name));
        addFilterToCurrentFilter("availability");
    }

    private void filterBySpecialFeature(String name) {
        ProductsManager.filterBySpecialFeature(name);
        addFilterToCurrentFilter("special feature");
    }

    private void filterByBrand(String name) {
        ProductsManager.filterByCompanyName(name);
        addFilterToCurrentFilter("brand");
    }

    private void filter(String type, String name) {
        if (type.equals("name")) {
            filterByName(name);
        } else if (type.equals("category")) {
            filterByCategory(name);
        } else if (type.equals("price")) {
            filterByPrice(name);
        } else if (type.equals("seller")) {
            filterBySeller(name);
        } else if (type.equals("availability")) {
            filterByAvailability(name);
        } else if (type.equals("special features")) {
            filterBySpecialFeature(name);
        } else if (type.equals("brand")) {
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
//        ProductsManager.disableFilterByName(name);
//        removeFilterFromCurrentFilter("name");
//    }
//
//    private void disableFilterByCategory() {
//        System.out.println("enter a category name: ");
//        String categoryName = scanner.nextLine();
//        ProductsManager.disableFilterByCategory(categoryName);
//        removeFilterFromCurrentFilter("category");
//    }
//
//    private void disableFilterByPrice() {
//        System.out.println("enter minimum price: ");
//        double min = scanner.nextDouble();
//        System.out.println("enter maximum price: ");
//        double max = scanner.nextDouble();
//        ProductsManager.disableFilterByPrice(min, max);
//        removeFilterFromCurrentFilter("price");
//    }
//
//    private void disableFilterBySeller() {
//        System.out.println("enter seller name: ");
//        String name = scanner.nextLine();
//        ProductsManager.disableFilterBySeller(name);
//        removeFilterFromCurrentFilter("seller");
//    }
//
//    private void disableFilterByAvailability() {
//        System.out.println("enter a state for availability"); //agar manzore mojodiate kalaro dorost gerefte basham
//        String state = scanner.nextLine();
//        ProductsManager.disableFilterByAvailability(ProductEnum.valueOf(state));
//        removeFilterFromCurrentFilter("availability");
//    }
//
//    private void disableFilterBySpecialFeature() {
//        System.out.println("enter special feature: ");
//        String feature = scanner.nextLine();
//        ProductsManager.disableFilterBySpecialFeature(feature);
//        removeFilterFromCurrentFilter("special features");
//    }
//
//    private void disableFilterByBrand() {
//        System.out.println("enter company name: ");
//        String name = scanner.nextLine();
//        ProductsManager.disableFilterByCompanyName(name);
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

//    public void sorting() {
//        String command;
//        while (true) {
//            command = scanner.nextLine();
//            Pattern sortPattern = Pattern.compile("sort\\s(.+)");
//            Matcher sortMatcher = sortPattern.matcher(command);
//            if (command.matches("sort\\s(.+)")) {
//                sortMatcher.find();
//                ProductsManager.sort(sortMatcher.group(1));
//                System.out.println(ProductsManager.getSortProductsName(ProductsManager.getCurrentSort()));
//            } else if (command.equals("show available sort")) {
//                System.out.println(ProductsManager.showAvailableSort());
//            } else if (command.equals("current sort")) {
//                System.out.println(ProductsManager.getCurrentSort());
//            } else if (command.matches("disable sort")) {
//                try {
//                    ProductsManager.disableSort(ProductsManager.getCurrentSort());
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
//    }

}