package View;

import Client.ClientController.AccountsController.CCustomerController;
import Client.ClientController.AccountsController.CManagerController;
import Client.ClientController.AccountsController.CSellerController;
import Client.ClientController.AccountsController.CSupporterController;
import Client.ClientController.CProductsController;
import Controller.ProductsManager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Product;
import Server.ServerController.AccountsController.CustomerController;
import Server.ServerController.AccountsController.ManagerController;
import Server.ServerController.AccountsController.SellerController;
import Server.ServerController.AccountsController.SupporterController;
import Server.ServerController.DataBaseForServer;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

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
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(25, 25, 25, 25));
        Label notify = new Label();
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
        backButton.setStyle(style);
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        accountsButton.setStyle(style);
        discountButton.setStyle(style);
        logoutButton.setStyle(style);
        addActionForMainButtons(accountsButton, discountButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, discountButton, logoutButton);
        HBox bar = new HBox(30);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                parentMenu.show();
                notify.setText("");
            }
        });
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableList(filterChoiceBoxItems()));
        TextField textField = new TextField();
        textField.setPromptText("filter by ...");
        textField.setStyle(style);
        Button submit = new Button("submit filter");
        submit.setStyle(style);
        choiceBox.setStyle(style);
        submit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
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
        filter.getChildren().addAll(choiceBox, textField, submit, notify);
        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableList(sortChoiceBoxItems()));
        choiceBox1.setStyle(style);
        Button submitButton = new Button("submit sort");
        submitButton.setStyle(style);
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                try {
                    ProductsManager.sort((String) choiceBox1.getValue());
                    setSortScene();
                } catch (Exception ignored) {
                }
            }
        });
        HBox sort = new HBox(5);
        sort.getChildren().addAll(choiceBox1, submitButton);

//        ChoiceBox category = new ChoiceBox(ProductsManager.showCategory());
        ChoiceBox category = new ChoiceBox(CProductsController.showCategories());
        bar.getChildren().addAll(backButton, mainButtons, filter, sort, category);
        category.setStyle(style);
        ArrayList<HBox> hBoxes = new ArrayList<>();
        hBoxes.add(bar);
//        for (Product product : ProductsManager.showProducts()) {
        for (Product product : DataBaseForServer.getAllProducts()) {
            HBox hBox = new HBox(10);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception e) {
            }
            ImageView imageView = new ImageView(image);
            Button button = new Button("show product");
            button.setStyle(style);
            button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.play();
                    handleProductPage(product);
                }
            });
            Text name = new Text("Product name: " + product.getName());
            Text price = new Text("Product price: " + product.getPrice() + "$");
            Text score = new Text("Product score: " + product.getPrice());
            Text x = new Text("Product score: " + product.getCategory());

            VBox vBox = new VBox(5);
            vBox.getChildren().addAll(name, price, score, x);
            hBox.getChildren().addAll(imageView, vBox, button);
            hBoxes.add(hBox);
        }
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(hBoxes);
        scrollPane.setContent(vBox);
        Scene scene = new Scene(scrollPane, 1270, 650);
        Menu.window.setScene(scene);
    }

    public void setFilterScene() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(25, 25, 25, 25));
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
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productsButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        accountsButton.setStyle(style);
        productsButton.setStyle(style);
        discountButton.setStyle(style);
        logoutButton.setStyle(style);
        addActionForMainButtonsForFilterAndSort(mediaPlayer, accountsButton, discountButton, productsButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productsButton, discountButton, logoutButton);
        HBox bar = new HBox(30);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                ProductsManager.setIsThereAnyFilter(false);
                parentMenu.show();
                notify.setText("");
            }
        });
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableList(filterChoiceBoxItems()));
        choiceBox.setStyle(style);
        TextField textField = new TextField();
        textField.setPromptText("filter by ...");
        textField.setStyle(style);
        Button submit = new Button("submit");
        submit.setStyle(style);
        submit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
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
        filter.getChildren().addAll(choiceBox, textField, submit, notify);
        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableList(sortChoiceBoxItems()));
        choiceBox1.setStyle(style);
        Button submitButton = new Button("submit sort");
        submitButton.setStyle(style);
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
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
        for (Product product : ProductsManager.getFilterProduct()) {
            HBox hBox = new HBox(10);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception e) {
            }
            ImageView imageView = new ImageView(image);
            Button button = new Button("show product");
            button.setStyle(style);
            button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.play();
                    handleProductPage(product);
                }
            });
            Text name = new Text("Product name: " + product.getName());
            Text price = new Text("Product price: " + product.getPrice() + "$");
            Text score = new Text("Product score: " + product.getPrice());

            VBox vBox = new VBox(5);
            vBox.getChildren().addAll(name, price, score);
            hBox.getChildren().addAll(imageView, vBox, button);
            hBoxes.add(hBox);
        }
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(hBoxes);
        scrollPane.setContent(vBox);
        Scene scene = new Scene(scrollPane, 1270, 650);
        Menu.window.setScene(scene);
    }

    public void setSortScene() {
        String path = "C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Sounds\\button.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(25, 25, 25, 25));
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
        Button backButton = new Button("Back");
        backButton.setStyle(style);
        HBox mainButtons = new HBox(3);
        mainButtons.setAlignment(Pos.TOP_RIGHT);
        Button accountsButton = new Button("Accounts");
        Button productsButton = new Button("Products");
        Button discountButton = new Button("Discounts");
        Button logoutButton = new Button("Logout");
        accountsButton.setStyle(style);
        productsButton.setStyle(style);
        discountButton.setStyle(style);
        logoutButton.setStyle(style);
        addActionForMainButtonsForFilterAndSort(mediaPlayer, accountsButton, discountButton, productsButton, logoutButton);
        mainButtons.getChildren().addAll(accountsButton, productsButton, discountButton, logoutButton);
        HBox bar = new HBox(30);
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                parentMenu.show();
            }
        });
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableList(filterChoiceBoxItems()));
        choiceBox.setStyle(style);
        TextField textField = new TextField();
        textField.setPromptText("filter by ...");
        textField.setStyle(style);
        Button submit = new Button("submit");
        submit.setStyle(style);
        submit.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
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
        filter.getChildren().addAll(choiceBox, textField, submit, notify);
        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableList(sortChoiceBoxItems()));
        choiceBox1.setStyle(style);
        Button submitButton = new Button("submit sort");
        submitButton.setStyle(style);
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
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
        for (Product product : ProductsManager.getSortProducts()) {
            HBox hBox = new HBox(10);
            Image image = null;
            try {
                FileInputStream inputStream = new FileInputStream(product.getPath());
                image = new Image(inputStream);
            } catch (Exception ignored) {
            }
            ImageView imageView = new ImageView(image);
            Button button = new Button("show product");
            button.setStyle(style);
            button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.play();
                    handleProductPage(product);
                }
            });
            Text name = new Text("Product name: " + product.getName());
            Text price = new Text("Product price: " + product.getPrice() + "$");
            Text score = new Text("Product score: " + product.getPrice());

            VBox vBox = new VBox(5);
            vBox.getChildren().addAll(name, price, score);
            hBox.getChildren().addAll(imageView, vBox, button);
            hBoxes.add(hBox);
        }
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(hBoxes);
        scrollPane.setContent(vBox);
        Scene scene = new Scene(scrollPane, 1270, 650);
        Menu.window.setScene(scene);
    }

    public void addActionForMainButtonsForFilterAndSort(MediaPlayer mediaPlayer, Button accountsButton, Button productsButton, Button discountButton, Button logoutButton) {
        accountsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                handleAccountsMenu();
            }
        });
        productsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                handleProductsMenu();
            }
        });
        discountButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
                handleDiscountsMenu();
            }
        });
        logoutButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.play();
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

//    private void filterByAvailability(String name) {
//        ProductsManager.filterByAvailability(ProductEnum.valueOf(name));
//        addFilterToCurrentFilter("availability");
//    }

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
        }
//        else if (type.equals("availability")) {
//            filterByAvailability(name);
//        }
        else if (type.equals("special features")) {
            filterBySpecialFeature(name);
        } else if (type.equals("brand")) {
            filterByBrand(name);
        }
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

//    public void handleLogout() {
//        if (RegisterCustomerMenu.getCurrentCustomer() != null) {
//            RegisterCustomerMenu.removeFromOnlineCustomer(RegisterCustomerMenu.getCurrentCustomer());
//            RegisterCustomerMenu.setCurrentCustomer(null);
//        } else if (RegisterSellerMenu.getCurrentSeller() != null) {
//            RegisterSellerMenu.removeFromOnlineSeller(RegisterSellerMenu.getCurrentSeller());
//            RegisterSellerMenu.setCurrentSeller(null);
//        } else if (RegisterManagerMenu.getCurrentManager() != null) {
//            RegisterManagerMenu.removeFromOnlineManager(RegisterManagerMenu.getCurrentManager());
//            RegisterManagerMenu.setCurrentManager(null);
//        } else if (RegisterSupporterMenu.getCurrentSupporter() != null) {
//            RegisterSupporterMenu.removeFromOnlineSupporter(RegisterSupporterMenu.getCurrentSupporter());
//            RegisterSupporterMenu.setCurrentSupporter(null);
//        }
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.show();
//    }

    public void handleLogout() {
        if (CustomerController.getCustomer() != null) {
            CustomerController.setCustomer(null);
            CCustomerController.setCustomer(null);
            CustomerController.removeOnlineCustomer(CustomerController.getCustomer());
            CCustomerController.removeOnlineCustomer(CCustomerController.getCustomer());
        } else if (SellerController.getSeller() != null) {
            SellerController.setSeller(null);
            CSellerController.setSeller(null);
            SellerController.removeOnlineSeller(SellerController.getSeller());
            CSellerController.removeOnlineSeller(CSellerController.getSeller());
        } else if (ManagerController.getManager() != null) {
            ManagerController.setManager(null);
            CManagerController.setManager(null);
            ManagerController.removeOnlineManger(ManagerController.getManager());
            CManagerController.removeOnlineManger(CManagerController.getManager());
        } else if (SupporterController.getSupporter() != null) {
            SupporterController.setSupporter(null);
            CSupporterController.setSupporter(null);
            SupporterController.removeOnlineSupporter(SupporterController.getSupporter());
            CSupporterController.removeOnlineSupporter(CSupporterController.getSupporter());
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