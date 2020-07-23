package Client.ClientController;

import Client.Client;
import Models.Accounts.Account;
import Models.Accounts.Seller;
import Models.Category;
import Models.Discount;
import Models.DiscountCode;
import Models.Product;
import Models.Request.Request;

import java.util.ArrayList;

public class CGetDataFromDataBase {

    public static Account getAccount(String username) throws Exception {

        String func = "Get Account";
        Client.sendMessage(func);

        Client.sendMessage(username);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("There isn't any account with this username");
        else
            return (Account) response;
    }

    public static Product getProduct(int productId) throws Exception {

        String func = "Get Product";
        Client.sendMessage(func);

        Client.sendObject(productId);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any product with this id");
        else
            return (Product) response;
    }


    public static Discount getOff(int offId) throws Exception {

        String func = "Get Off";
        Client.sendMessage(func);

        Client.sendObject(offId);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any discount with this id");
        else
            return (Discount) response;
    }

    public static Request getRequest(int requestId) throws Exception {

        String func = "Get Request";
        Client.sendMessage(func);

        Client.sendObject(requestId);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any request with this id");
        else
            return (Request) response;
    }

    public static DiscountCode getSale(String saleCode) throws Exception {

        String func = "Get Sale";
        Client.sendMessage(func);

        Client.sendObject(saleCode);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn;t any discount code with this code");
        else
            return (DiscountCode) response;
    }

    public static Category getCategory(String categoryName) throws Exception {

        String func = "Get Category";
        Client.sendMessage(func);

        Client.sendObject(categoryName);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any category with this name");
        else
            return (Category) response;
    }

    public static boolean checkIfAnyAdminExists() {

        String func = "Check If Any Admin Exists";
        Client.sendMessage(func);

        return (boolean) Client.receiveObject();
//        return new File("Resources/Accounts/Admin").listFiles().length==0;
    }

    public static ArrayList<Seller> findSellersFromProductId(int productId) {

        String func = "Find Sellers From Product Id";
        Client.sendMessage(func);

        Client.sendObject(productId);

        return (ArrayList<Seller>) Client.receiveObject();
    }
}
