package View;

import Controller.DiscountManager;
import Models.Enums.ProductEnum;
import Models.Product;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscountsMenu extends Menu {
    private ArrayList<String> currentFilters = new ArrayList<>();

    public DiscountsMenu(Menu parentMenu) {
        super("Discounts Menu", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.offs");
        System.out.println("2.show Product");
        System.out.println("3.filtering");
        System.out.println("4.sorting");
        System.out.println("5.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            offs();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            showProducts();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 3) {
            filtering();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 4) {
            sorting();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 5) {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    public void offs() {
        System.out.println(DiscountManager.showDiscountProducts());
    }

    public void showProducts() {
        String command;
        System.out.println(DiscountManager.showDiscountProducts());
        while (true) {
            command = scanner.nextLine();
            Pattern showProductByIdPattern = Pattern.compile("show product\\s(.+)");
            Matcher showProductByIdMatcher = showProductByIdPattern.matcher(command);
            if (command.matches("show product\\s(.+)")) {
                showProductByIdMatcher.find();
                Product product = Product.getProductWithId(showProductByIdMatcher.group(1));
                ProductMenu productMenu = (ProductMenu)(Menu.getMenu("Product Menu"));
                productMenu.setParentMenu(this);
                productMenu.setProduct(product);
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

    public void filtering() {
        String command;
        DiscountManager.addAllProductsToFilterProducts();
        while (true) {
            command = scanner.nextLine();
            Pattern filterPattern = Pattern.compile("filter\\s(.+)");
            Matcher filterMatcher = filterPattern.matcher(command);
            Pattern disableFilterPattern = Pattern.compile("disable filter\\s(.+)");
            Matcher disableFilterMatcher = disableFilterPattern.matcher(command);
            if (command.equals("show available filters")) {
                System.out.println("available fil");
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
                System.out.println(DiscountManager.showAvailableSort());
            } else if (command.matches("sort\\s(.+)")) {
                sortMatcher.find();
                DiscountManager.sort(sortMatcher.group(1));
                System.out.println(DiscountManager.getSortProductsName(DiscountManager.getCurrentSort()));
            } else if (command.equals("current sort")) {
                System.out.println(DiscountManager.getCurrentSort());
            } else if (command.matches("disable sort")) {
                try {
                    DiscountManager.disableSort(DiscountManager.getCurrentSort());
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
        DiscountManager.filterByName(name);
        System.out.println(DiscountManager.getFilterProductsName());
        addFilterToCurrentFilter("name");
    }

    private void filterByCategory() {
        System.out.println("enter a category name: ");
        String categoryName = scanner.nextLine();
        DiscountManager.filterByCategory(categoryName);
        System.out.println(DiscountManager.getFilterProductsName());
        addFilterToCurrentFilter("category");
    }

    private void filterByPrice() {
        System.out.println("enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.println("enter maximum price: ");
        double max = scanner.nextDouble();
        DiscountManager.filterByPrice(min, max);
        System.out.println(DiscountManager.getFilterProductsName());
        addFilterToCurrentFilter("price");
    }

    private void filterBySeller() {
        System.out.println("enter seller name: ");
        String name = scanner.nextLine();
        DiscountManager.filterBySeller(name);
        System.out.println(DiscountManager.getFilterProductsName());
        addFilterToCurrentFilter("seller");
    }

    private void filterByAvailability() {
        System.out.println("enter a state for availability"); //agar manzore mojodiate kalaro dorost gerefte basham
        String state = scanner.nextLine();
        DiscountManager.filterByAvailability(ProductEnum.valueOf(state));
        System.out.println(DiscountManager.getFilterProductsName());
        addFilterToCurrentFilter("availability");
    }

    private void filterBySpecialFeature() {
        System.out.println("enter special feature: ");
        String feature = scanner.nextLine();
        DiscountManager.filterBySpecialFeature(feature);
        System.out.println(DiscountManager.getFilterProductsName());
        addFilterToCurrentFilter("special feature");
    }

    private void filterByBrand() {
        System.out.println("enter company name: ");
        String name = scanner.nextLine();
        DiscountManager.filterByCompanyName(name);
        System.out.println(DiscountManager.getFilterProductsName());
        addFilterToCurrentFilter("brand");
    }

    private void disableFilterByName() {
        System.out.println("enter a name");
        String name = scanner.nextLine();
        DiscountManager.disableFilterByName(name);
        removeFilterFromCurrentFilter("name");
    }

    private void disableFilterByCategory() {
        System.out.println("enter a category name: ");
        String categoryName = scanner.nextLine();
        DiscountManager.disableFilterByCategory(categoryName);
        removeFilterFromCurrentFilter("category");
    }

    private void disableFilterByPrice() {
        System.out.println("enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.println("enter maximum price: ");
        double max = scanner.nextDouble();
        DiscountManager.disableFilterByPrice(min, max);
        removeFilterFromCurrentFilter("price");
    }

    private void disableFilterBySeller() {
        System.out.println("enter seller name: ");
        String name = scanner.nextLine();
        DiscountManager.disableFilterBySeller(name);
        removeFilterFromCurrentFilter("seller");
    }

    private void disableFilterByAvailability() {
        System.out.println("enter a state for availability");
        String state = scanner.nextLine();
        DiscountManager.disableFilterByAvailability(ProductEnum.valueOf(state));
        removeFilterFromCurrentFilter("availability");
    }

    private void disableFilterBySpecialFeature() {
        System.out.println("enter special feature: ");
        String feature = scanner.nextLine();
        DiscountManager.disableFilterBySpecialFeature(feature);
        removeFilterFromCurrentFilter("special features");
    }

    private void disableFilterByBrand() {
        System.out.println("enter company name: ");
        String name = scanner.nextLine();
        DiscountManager.disableFilterByCompanyName(name);
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
