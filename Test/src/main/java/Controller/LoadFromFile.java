package Controller;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Logs.Log;
import Models.Request.Request;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFromFile {
    public static void load(Gson gson) throws FileNotFoundException {
        loadCustomersFromFile(gson);
        loadSellersFromFile(gson);
        loadManagersFromFile(gson);
        loadRequestFromFile(gson);
        loadProductsFromFile(gson);
        loadLogsFromFile(gson);
        loadDiscountProductsFromFile(gson);
        loadDiscountCodesFromFile(gson);
        loadCategoriesFromFile(gson);
        loadCartsFromFile(gson);
        loadPointOfViewsFromFile(gson);
    }

    private static void loadCustomersFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\customers.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()){
            Customer.getAllCustomers().add(gson.fromJson((scanner.nextLine()), Customer.class));
        }
    }

    private static void loadSellersFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\sellers.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()){
            Seller.getAllSellers().add(gson.fromJson((scanner.nextLine()), Seller.class));
        }
    }

    private static void loadManagersFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\managers.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()){
            Manager.getAllManagers().add(gson.fromJson((scanner.nextLine()), Manager.class));
        }
    }

    private static void loadRequestFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\requests.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()){
            Request.getAllRequests().add(gson.fromJson((scanner.nextLine()), Request.class));
        }
    }

    private static void loadProductsFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\products.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()){
            Product.getAllProducts().add(gson.fromJson((scanner.nextLine()), Product.class));
        }
    }

    private static void loadLogsFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\logs.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()){
            Log.getAllLogs().add(gson.fromJson((scanner.nextLine()), Log.class));
        }
    }

    private static void loadDiscountProductsFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\discountProducts.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()){
            Discount.getDiscountProducts().add(gson.fromJson((scanner.nextLine()), Product.class));
        }
    }

    private static void loadDiscountCodesFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\discountCodes.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            DiscountCode.getAllDiscountCodes().add(gson.fromJson((scanner.nextLine()), DiscountCode.class));
        }
    }

    private static void loadCategoriesFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\categories.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()){
            Category.getAllCategories().add(gson.fromJson((scanner.nextLine()), Category.class));
        }
    }

    private static void loadCartsFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\carts.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            Cart.getAllCarts().add(gson.fromJson((scanner.nextLine()), Cart.class));
        }
    }

    private static void loadPointOfViewsFromFile(Gson gson) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\pointOfViews.txt");
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            PointOfView.getAllPointOfViews().add(gson.fromJson((scanner.nextLine()), PointOfView.class));
        }
    }
}
