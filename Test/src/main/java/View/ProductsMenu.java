package View;

import Controller.ProductsManager;
import Models.Enums.ProductEnum;
import Models.Product;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductsMenu extends Menu {
    private ArrayList<String> currentFilters = new ArrayList<>();

    public ProductsMenu(Menu parentMenu) {
        super("Products", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.view categories");
        System.out.println("2.filtering");
        System.out.println("3.sorting");
        System.out.println("4.show products");
        System.out.println("5.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            viewCategories();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            filtering();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 3) {
            sorting();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 4) {
            showProducts();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 5) {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void viewCategories() {
        System.out.println(ProductsManager.showCategory());
    }

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
                System.out.println("filter [an available filter]");
                System.out.println("current filters");
                System.out.println("disable filter [a selected filter]");
                System.out.println("back");
            }
        }

    }

    public void sorting() {
        String command;
        while (true) {
            command = scanner.nextLine();
            Pattern sortPattern = Pattern.compile("sort\\s(.+)");
            Matcher sortMatcher = sortPattern.matcher(command);
            if (command.equals("show available sort")) {
                System.out.println(ProductsManager.showAvailableSort());
            } else if (command.matches("sort\\s(.+)")) {
                sortMatcher.find();
                ProductsManager.sort(sortMatcher.group(1));
                System.out.println(ProductsManager.getSortProductsName(ProductsManager.getCurrentSort()));
            } else if (command.equals("current sort")) {
                System.out.println(ProductsManager.getCurrentSort());
            } else if (command.matches("disable sort")) {
                try {
                    ProductsManager.disableSort(ProductsManager.getCurrentSort());
                } catch (Exception e) {
                    e.getMessage();
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

    public void showProducts() {
        String command;
        ProductsManager.showProducts();
        while (true) {
            command = scanner.nextLine();
            Pattern showProductByIdPattern = Pattern.compile("show product\\s(.+)");
            Matcher showProductByIdMatcher = showProductByIdPattern.matcher(command);
            if (command.matches("show product\\s(.+)")) {
                showProductByIdMatcher.find();
                Product product = Product.getProductWithId(showProductByIdMatcher.group(1));
                ProductMenu productMenu = new ProductMenu(this, product);
                productMenu.show();
                productMenu.execute();
            } else if (command.equals("help")) {
                System.out.println("commands that you can enter are:");
                System.out.println("show product [productID]");
                System.out.println("back");
            } else if (command.equals("back")) {
                break;
            } else System.out.println("Command is invalid");
        }

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
        System.out.println("enter a state for availability"); //agar manzore mojodiate kalaro dorost gerefte basham
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
}