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
        // age residim ino ghesmate filter va disable esho method konim tabehe kotahtar she
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
            } else if (command.matches("filter by\\s(.+)")) {
                filterMatcher.find();
                if (filterMatcher.group(1).equals("name")) {
                    System.out.println("enter a name");
                    String name = scanner.nextLine();
                    ProductsManager.filterByName(name);
                    System.out.println(ProductsManager.getFilterProductsName());
                    addFilterToCurrentFilter("name");
                } else if (filterMatcher.group(1).equals("category")) {
                    System.out.println("enter a category name: ");
                    String categoryName = scanner.nextLine();
                    ProductsManager.filterByCategory(categoryName);
                    System.out.println(ProductsManager.getFilterProductsName());
                    addFilterToCurrentFilter("category");
                } else if (filterMatcher.group(1).equals("price")) {
                    System.out.println("enter minimum price: ");
                    double min = scanner.nextDouble();
                    System.out.println("enter maximum price: ");
                    double max = scanner.nextDouble();
                    ProductsManager.filterByPrice(min, max);
                    System.out.println(ProductsManager.getFilterProductsName());
                    addFilterToCurrentFilter("price");
                } else if (filterMatcher.group(1).equals("seller")) {
                    System.out.println("enter seller name: ");
                    String name = scanner.nextLine();
                    ProductsManager.filterBySeller(name);
                    System.out.println(ProductsManager.getFilterProductsName());
                    addFilterToCurrentFilter("seller");
                } else if (filterMatcher.group(1).equals("availability")) {
                    System.out.println("enter a state for availability"); //agar manzore mojodiate kalaro dorost gerefte basham
                    String state = scanner.nextLine();
                    ProductsManager.filterByAvailability(ProductEnum.valueOf(state));
                    System.out.println(ProductsManager.getFilterProductsName());
                    addFilterToCurrentFilter("availability");
                } else if (filterMatcher.group(1).equals("special features")) {
                    System.out.println("enter special feature: ");
                    String feature = scanner.nextLine();
                    ProductsManager.filterBySpecialFeature(feature);
                    System.out.println(ProductsManager.getFilterProductsName());
                    addFilterToCurrentFilter("special feature");
                } else if (filterMatcher.group(1).equals("brand")) {
                    System.out.println("enter company name: ");
                    String name = scanner.nextLine();
                    ProductsManager.filterByCompanyName(name);
                    System.out.println(ProductsManager.getFilterProductsName());
                    addFilterToCurrentFilter("brand");
                }
            } else if (command.equals("current filters")) {
                System.out.println(currentFilters);
            } else if (command.matches("disable filter\\s(.+)")) {
                disableFilterMatcher.find();
                if (disableFilterMatcher.group(1).equals("name")) {
                    System.out.println("enter a name");
                    String name = scanner.nextLine();
                    ProductsManager.disableFilterByName(name);
                    removeFilterFromCurrentFilter("name");
                } else if (disableFilterMatcher.group(1).equals("category")) {
                    System.out.println("enter a category name: ");
                    String categoryName = scanner.nextLine();
                    ProductsManager.disableFilterByCategory(categoryName);
                    removeFilterFromCurrentFilter("category");
                } else if (disableFilterMatcher.group(1).equals("price")) {
                    System.out.println("enter minimum price: ");
                    double min = scanner.nextDouble();
                    System.out.println("enter maximum price: ");
                    double max = scanner.nextDouble();
                    ProductsManager.disableFilterByPrice(min, max);
                    removeFilterFromCurrentFilter("price");
                } else if (disableFilterMatcher.group(1).equals("seller")) {
                    System.out.println("enter seller name: ");
                    String name = scanner.nextLine();
                    ProductsManager.disableFilterBySeller(name);
                    removeFilterFromCurrentFilter("seller");
                } else if (disableFilterMatcher.group(1).equals("availability")) {
                    System.out.println("enter a state for availability"); //agar manzore mojodiate kalaro dorost gerefte basham
                    String state = scanner.nextLine();
                    ProductsManager.disableFilterByAvailability(ProductEnum.valueOf(state));
                    removeFilterFromCurrentFilter("availability");
                } else if (disableFilterMatcher.group(1).equals("special features")) {
                    System.out.println("enter special feature: ");
                    String feature = scanner.nextLine();
                    ProductsManager.disableFilterBySpecialFeature(feature);
                    removeFilterFromCurrentFilter("special features");
                } else if (disableFilterMatcher.group(1).equals("brand")) {
                    System.out.println("enter company name: ");
                    String name = scanner.nextLine();
                    ProductsManager.disableFilterByCompanyName(name);
                    removeFilterFromCurrentFilter("brand");
                }
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

}