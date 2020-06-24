package View;

import Controller.ProductsManager;
import Models.Enums.ProductEnum;
import Models.Product;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //TODO add choice box for filter and sort
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
        //TODO check spacing
        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                parentMenu.show();
            }
        });
        bar.getChildren().addAll(backButton, mainButtons);
        ArrayList<HBox> hBoxes = new ArrayList<>();
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

    private void addFilterToCurrentFilter(String name) {
        for (String filter : currentFilters) {
            if (!(filter.contains(name))) {
                currentFilters.add(name);
            }
        }
    }

    private void removeFilterFromCurrentFilter(String name) {
        for (String filter : currentFilters) {
            if (filter.contains(name)) {
                currentFilters.remove(name);
            }
        }
    }

    private void filterByName() {
        System.out.println("enter a name");
        String name = scanner.nextLine();
        ProductsManager.filterByName(name);
        System.out.println(ProductsManager.getFilterProductsName());
        addFilterToCurrentFilter("name");
    }

    private void filterByCategory() {
        System.out.println("enter a category name: ");
        String categoryName = scanner.nextLine();
        ProductsManager.filterByCategory(categoryName);
        System.out.println(ProductsManager.getFilterProductsName());
        addFilterToCurrentFilter("category");
    }

    private void filterByPrice() {
        System.out.println("enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.println("enter maximum price: ");
        double max = scanner.nextDouble();
        ProductsManager.filterByPrice(min, max);
        System.out.println(ProductsManager.getFilterProductsName());
        addFilterToCurrentFilter("price");
    }

    private void filterBySeller() {
        System.out.println("enter seller name: ");
        String name = scanner.nextLine();
        ProductsManager.filterBySeller(name);
        System.out.println(ProductsManager.getFilterProductsName());
        addFilterToCurrentFilter("seller");
    }

    private void filterByAvailability() {
        System.out.println("enter a state for availability");
        String state = scanner.nextLine();
        ProductsManager.filterByAvailability(ProductEnum.valueOf(state));
        System.out.println(ProductsManager.getFilterProductsName());
        addFilterToCurrentFilter("availability");
    }

    private void filterBySpecialFeature() {
        System.out.println("enter special feature: ");
        String feature = scanner.nextLine();
        ProductsManager.filterBySpecialFeature(feature);
        System.out.println(ProductsManager.getFilterProductsName());
        addFilterToCurrentFilter("special feature");
    }

    private void filterByBrand() {
        System.out.println("enter company name: ");
        String name = scanner.nextLine();
        ProductsManager.filterByCompanyName(name);
        System.out.println(ProductsManager.getFilterProductsName());
        addFilterToCurrentFilter("brand");
    }

    private void disableFilterByName() {
        System.out.println("enter a name");
        String name = scanner.nextLine();
        ProductsManager.disableFilterByName(name);
        removeFilterFromCurrentFilter("name");
    }

    private void disableFilterByCategory() {
        System.out.println("enter a category name: ");
        String categoryName = scanner.nextLine();
        ProductsManager.disableFilterByCategory(categoryName);
        removeFilterFromCurrentFilter("category");
    }

    private void disableFilterByPrice() {
        System.out.println("enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.println("enter maximum price: ");
        double max = scanner.nextDouble();
        ProductsManager.disableFilterByPrice(min, max);
        removeFilterFromCurrentFilter("price");
    }

    private void disableFilterBySeller() {
        System.out.println("enter seller name: ");
        String name = scanner.nextLine();
        ProductsManager.disableFilterBySeller(name);
        removeFilterFromCurrentFilter("seller");
    }

    private void disableFilterByAvailability() {
        System.out.println("enter a state for availability"); //agar manzore mojodiate kalaro dorost gerefte basham
        String state = scanner.nextLine();
        ProductsManager.disableFilterByAvailability(ProductEnum.valueOf(state));
        removeFilterFromCurrentFilter("availability");
    }

    private void disableFilterBySpecialFeature() {
        System.out.println("enter special feature: ");
        String feature = scanner.nextLine();
        ProductsManager.disableFilterBySpecialFeature(feature);
        removeFilterFromCurrentFilter("special features");
    }

    private void disableFilterByBrand() {
        System.out.println("enter company name: ");
        String name = scanner.nextLine();
        ProductsManager.disableFilterByCompanyName(name);
        removeFilterFromCurrentFilter("brand");
    }

    private void filter(String command) {
        if (command.equals("name")) {
            filterByName();
        } else if (command.equals("category")) {
            filterByCategory();
        } else if (command.equals("price")) {
            filterByPrice();
        } else if (command.equals("seller")) {
            filterBySeller();
        } else if (command.equals("availability")) {
            filterByAvailability();
        } else if (command.equals("special features")) {
            filterBySpecialFeature();
        } else if (command.equals("brand")) {
            filterByBrand();
        }
    }

    private void disableFilter(String command) {
        if (command.equals("name")) {
            disableFilterByName();
        } else if (command.equals("category")) {
            disableFilterByCategory();
        } else if (command.equals("price")) {
            disableFilterByPrice();
        } else if (command.equals("seller")) {
            disableFilterBySeller();
        } else if (command.equals("availability")) {
            disableFilterByAvailability();
        } else if (command.equals("special features")) {
            disableFilterBySpecialFeature();
        } else if (command.equals("brand")) {
            disableFilterByBrand();
        }
    }

    //check
    public void viewCategories() {
        System.out.println(ProductsManager.showCategory());
    }

    //bug
    public void filtering() {
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern filterPattern = Pattern.compile("filter\\s(.+)");
            Matcher filterMatcher = filterPattern.matcher(command);
            Pattern disableFilterPattern = Pattern.compile("disable filter\\s(.+)");
            Matcher disableFilterMatcher = disableFilterPattern.matcher(command);
            if (command.equals("show available filters")) {
                System.out.println("available filters are: ");
                System.out.println(ProductsManager.showAvailableFilter());
            } else if (command.matches("filter\\s(.+)")) {
                filterMatcher.find();
                filter(filterMatcher.group(1));
            } else if (command.equals("current filters")) {
                System.out.println(currentFilters);
            } else if (command.matches("disable filter\\s(.+)")) {
                disableFilterMatcher.find();
                disableFilter(disableFilterMatcher.group(1));
            } else if (command.equals("back")) {
                break;
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("show available filters");
                System.out.println("filter [an available filter]");
                System.out.println("current filters");
                System.out.println("disable filter [a selected filter]");
                System.out.println("back");
            }
        }

    }

    //bug
    public void sorting() {
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern sortPattern = Pattern.compile("sort\\s(.+)");
            Matcher sortMatcher = sortPattern.matcher(command);
            if (command.matches("sort\\s(.+)")) {
                sortMatcher.find();
                ProductsManager.sort(sortMatcher.group(1));
                System.out.println(ProductsManager.getSortProductsName(ProductsManager.getCurrentSort()));
            } else if (command.equals("show available sort")) {
                System.out.println(ProductsManager.showAvailableSort());
            } else if (command.equals("current sort")) {
                System.out.println(ProductsManager.getCurrentSort());
            } else if (command.matches("disable sort")) {
                try {
                    ProductsManager.disableSort(ProductsManager.getCurrentSort());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.matches("back")) {
                break;
            } else if (command.matches("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("sort [an available sort]");
                System.out.println("current sort");
                System.out.println("disable sort");
                System.out.println("back");
            }
        }
    }

}