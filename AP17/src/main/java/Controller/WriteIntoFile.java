package Controller;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Logs.Log;
import Models.Request.Request;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class WriteIntoFile {
    public static void writeCustomersIntoFile(Gson gson) throws IOException {
        // Gson gson = new Gson();
        System.out.println(3);
       // FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\customers.txt", true);
        // FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\customers.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\customers.txt");
        System.out.println(4);
        for (Customer customer : Customer.getAllCustomers()) {
            System.out.println(5);
            String toWrite = gson.toJson(customer) + "\n";
            fileOutputStream.write(toWrite.getBytes());
            fileOutputStream.flush();
            System.out.println(8);
        }
        System.out.println(6);
        fileOutputStream.close();
        System.out.println(7);


    }

    public static void writeManagersIntoFile(Gson gson) throws IOException {
        // Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\managers.txt");
        // FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\managers.txt");
        for (Manager manager : Manager.getAllManagers()) {
            fileWriter.append(gson.toJson(manager) + "\n");
        }
        fileWriter.close();
    }

    public static void writeSellersIntoFile(Gson gson) throws IOException {
        // Gson gson = new Gson();
        System.out.println(10);
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\sellers.txt");
        System.out.println(11);
        //FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\sellers.txt");
        for (Seller seller : Seller.getAllSellers()) {
            System.out.println(12);
            fileWriter.append(gson.toJson(seller) + "\n");
            System.out.println(13);
        }
        System.out.println(14);
        fileWriter.close();
        System.out.println(15);
    }

    public static void writeRequestsIntoFile(Gson gson) throws IOException {
        // Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\requests.txt");
        //FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\requests.txt");
        for (Request request : Request.getAllRequests()) {
            fileWriter.append(gson.toJson(request) + "\n");
        }
        fileWriter.close();
    }

    public static void writeProductsIntoFile(Gson gson) throws IOException {
        // Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\products.txt");
        //FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\products.txt");
        for (Product product : Product.getAllProducts()) {
            fileWriter.append(gson.toJson(product) + "\n");
        }
        fileWriter.close();
    }

    public static void writeLogsIntoFile(Gson gson) throws IOException {
        //  Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\logs.txt");
        //FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\logs.txt");
        for (Log log : Log.getAllLogs()) {
            fileWriter.append(gson.toJson(log) + "\n");
        }
        fileWriter.close();
    }

    public static void writeDiscountProductsIntoFile(Gson gson) throws IOException {
        //Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\discountProducts.txt");
        // FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\discountProducts.txt");
        for (Product discountProduct : Discount.getDiscountProducts()) {
            fileWriter.append(gson.toJson(discountProduct) + "\n");
        }
        fileWriter.close();
    }

    public static void writeDiscountCodesIntoFile(Gson gson) throws IOException {
        //Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\discountCodes.txt");
        // FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\discountCodes.txt");
        for (DiscountCode discountCode : DiscountCode.getAllDiscountCodes()) {
            fileWriter.append(gson.toJson(discountCode) + "\n");
        }
        fileWriter.close();
    }

    public static void writeCategoriesIntoFile(Gson gson) throws IOException {
        //  Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\categories.txt");
        // FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\categories.txt");
        for (Category category : Category.getAllCategories()) {
            fileWriter.append(gson.toJson(category) + "\n");
        }
        fileWriter.close();
    }

    public static void writeCartsIntoFile(Gson gson) throws IOException {
        //  Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\carts.txt");
        //FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\carts.txt");
        for (Cart cart : Cart.getAllCarts()) {
            fileWriter.append(gson.toJson(cart) + "\n");
        }
        fileWriter.close();
    }

    public static void writePointOfViewsIntoFile(Gson gson) throws IOException {
        // Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\AP17\\src\\main\\java\\Resources\\pointOfViews.txt");
        //FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\pointOfViews.txt");
        for (PointOfView pointOfView : PointOfView.getAllPointOfViews()) {
            fileWriter.append(gson.toJson(pointOfView) + "\n");
        }
        fileWriter.close();
    }

    public static void writeIntoFile(Gson gson) throws IOException {
        writeCustomersIntoFile(gson);
        writeCartsIntoFile(gson);
        writeSellersIntoFile(gson);
        writeCategoriesIntoFile(gson);
        writeDiscountCodesIntoFile(gson);
        writeDiscountProductsIntoFile(gson);
        writeManagersIntoFile(gson);
        writePointOfViewsIntoFile(gson);
        writeProductsIntoFile(gson);
        writeRequestsIntoFile(gson);
        writeLogsIntoFile(gson);
    }

}
