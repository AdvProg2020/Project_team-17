        package View;

import Controller.DiscountManager;
import Controller.ProductsManager;
import Models.Enums.ProductEnum;

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
        DiscountManager.showDiscountProducts();
        while (true) {
            command = scanner.nextLine();
            Pattern showProductByIdPattern = Pattern.compile("show product\\s(.+)");
            Matcher showProductByIdMatcher = showProductByIdPattern.matcher(command);
            if (command.matches("show product\\\\s(.+)")) {
                showProductByIdMatcher.find();
                //in k alan miad inja mibine id product ro baed mire to safeye hamon mahsol chejori handle konim k id
                //ro gereft bere to safeye hamoon product
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
        while (true){
            command=scanner.nextLine();
            Pattern filterPattern = Pattern.compile("filter\\s(.+)");
            Matcher filterMatcher = filterPattern.matcher(command);
            Pattern disableFilterPattern = Pattern.compile("disable filter\\s(.+)");
            Matcher disableFilterMatcher = disableFilterPattern.matcher(command);
            if(command.equals("show available filters")){
                System.out.println("available fil");
            }else if(command.matches("filter\\s(.+)")){
                filterMatcher.find();
                if (filterMatcher.group(1).equals("name")) {
                    System.out.println("enter a name");
                    String name = scanner.nextLine();
                    DiscountManager.filterByName(name);
                    System.out.println(DiscountManager.getFilterProductsName());
                    addFilterToCurrentFilter("name");
                } else if (filterMatcher.group(1).equals("category")) {
                    System.out.println("enter a category name: ");
                    String categoryName = scanner.nextLine();
                    DiscountManager.filterByCategory(categoryName);
                    System.out.println(DiscountManager.getFilterProductsName());
                    addFilterToCurrentFilter("category");
                } else if (filterMatcher.group(1).equals("price")) {
                    System.out.println("enter minimum price: ");
                    double min = scanner.nextDouble();
                    System.out.println("enter maximum price: ");
                    double max = scanner.nextDouble();
                    DiscountManager.filterByPrice(min, max);
                    System.out.println(DiscountManager.getFilterProductsName());
                    addFilterToCurrentFilter("price");
                } else if (filterMatcher.group(1).equals("seller")) {
                    System.out.println("enter seller name: ");
                    String name = scanner.nextLine();
                    DiscountManager.filterBySeller(name);
                    System.out.println(DiscountManager.getFilterProductsName());
                    addFilterToCurrentFilter("seller");
                } else if (filterMatcher.group(1).equals("availability")) {
                    System.out.println("enter a state for availability"); //agar manzore mojodiate kalaro dorost gerefte basham
                    String state = scanner.nextLine();
                    DiscountManager.filterByAvailability(ProductEnum.valueOf(state));
                    System.out.println(DiscountManager.getFilterProductsName());
                    addFilterToCurrentFilter("availability");
                } else if (filterMatcher.group(1).equals("special features")) {
                    System.out.println("enter special feature: ");
                    String feature = scanner.nextLine();
                    DiscountManager.filterBySpecialFeature(feature);
                    System.out.println(DiscountManager.getFilterProductsName());
                    addFilterToCurrentFilter("special feature");
                } else if (filterMatcher.group(1).equals("brand")) {
                    System.out.println("enter company name: ");
                    String name = scanner.nextLine();
                    DiscountManager.filterByCompanyName(name);
                    System.out.println(DiscountManager.getFilterProductsName());
                    addFilterToCurrentFilter("brand");
                }
            }else if (command.equals("current filters")) {
                System.out.println(currentFilters);
            } else if (command.matches("disable filter\\s(.+)")) {
                disableFilterMatcher.find();
                if (disableFilterMatcher.group(1).equals("name")) {
                    System.out.println("enter a name");
                    String name = scanner.nextLine();
                    DiscountManager.disableFilterByName(name);
                    removeFilterFromCurrentFilter("name");
                } else if (disableFilterMatcher.group(1).equals("category")) {
                    System.out.println("enter a category name: ");
                    String categoryName = scanner.nextLine();
                    DiscountManager.disableFilterByCategory(categoryName);
                    removeFilterFromCurrentFilter("category");
                } else if (disableFilterMatcher.group(1).equals("price")) {
                    System.out.println("enter minimum price: ");
                    double min = scanner.nextDouble();
                    System.out.println("enter maximum price: ");
                    double max = scanner.nextDouble();
                    DiscountManager.disableFilterByPrice(min, max);
                    removeFilterFromCurrentFilter("price");
                } else if (disableFilterMatcher.group(1).equals("seller")) {
                    System.out.println("enter seller name: ");
                    String name = scanner.nextLine();
                    DiscountManager.disableFilterBySeller(name);
                    removeFilterFromCurrentFilter("seller");
                } else if (disableFilterMatcher.group(1).equals("availability")) {
                    System.out.println("enter a state for availability");
                    String state = scanner.nextLine();
                    DiscountManager.disableFilterByAvailability(ProductEnum.valueOf(state));
                    removeFilterFromCurrentFilter("availability");
                } else if (disableFilterMatcher.group(1).equals("special features")) {
                    System.out.println("enter special feature: ");
                    String feature = scanner.nextLine();
                    DiscountManager.disableFilterBySpecialFeature(feature);
                    removeFilterFromCurrentFilter("special features");
                } else if (disableFilterMatcher.group(1).equals("brand")) {
                    System.out.println("enter company name: ");
                    String name = scanner.nextLine();
                    DiscountManager.disableFilterByCompanyName(name);
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