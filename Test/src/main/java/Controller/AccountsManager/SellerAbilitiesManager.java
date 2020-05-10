package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Seller;
import Models.Enums.ProductEnum;
import Models.Request.AddProductRequest;
import Models.Request.Request;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerAbilitiesManager {
    public void viewAccount(String username) throws Exception {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer customer = Customer.getCustomerByName(username);
            customer.toString();
        } else {
            throw new Exception("There hasn't been any registered account with this username!");
        }
    }

    public static void changeField(Seller seller, String field, String newContentForThisField) {
        if (field.equalsIgnoreCase("first name")) {
            seller.changeFirstName(seller, newContentForThisField);
        } else if (field.equalsIgnoreCase("last name")) {
            seller.changeLastName(seller, newContentForThisField);
        } else if (field.equalsIgnoreCase("email")) {
            seller.changeEmail(seller, newContentForThisField);
        } else if (field.equalsIgnoreCase("phone number")) {
            seller.changePhoneNumber(seller, newContentForThisField);
        } else if (field.equalsIgnoreCase("password")) {
            seller.changePassword(seller, newContentForThisField);
            //nemidonam type passwordam bayad check konim ya na
        }
    }

    public String viewFactoryInfo(Seller seller) {
        return seller.getCompanyName();
    }

    //view saleshistory ro bayad bezanim

    public static Product addProduct(String productId, ProductEnum productStatus, String productName, String companyName,
                           double price, Category category, Seller seller,String productExplanation) {
        Product product =new Product(productId, productName, companyName, price,
                seller, category,productExplanation,0);
        product.setProductState(ProductEnum.PRODUCING);
        return product;
    }


    public static Product editProduct(String id, String field, String newContentForThisField) {
        Product product = Product.getProductWithId(id);
        if (field.equals("name")) {
            product.setName(newContentForThisField);
        } else if (field.equals("companyName")) {
            product.setCompanyName(newContentForThisField);
        } else if (field.equals("description")) {
            product.setExplanation(newContentForThisField);
        } else if (field.equals("seller")) {
            product.setSeller(Seller.getSellerByName(newContentForThisField));
        }  else if (field.equals("price")) {
            product.setPrice(Double.parseDouble(newContentForThisField));
        } else if (field.equals("category")) {
            product.setCategory(Category.getCategoryByName(newContentForThisField));
        }
        product.setProductState(ProductEnum.EDITING);
        return product;
    }

    public static void removeProduct(Seller seller, String productId) throws Exception {
        if (Product.isThereProductWithId(productId)) {
            Product product = Product.getProductWithId(productId);
            if (seller.doesSellerHaveThisProduct(product)) {
                Product.removeProduct(product);
                seller.removeProduct(seller, product);
            } else {
                throw new Exception("This seller doesn't have this product!");
            }
        } else {
            throw new Exception("There isn't product with this ID!");
        }
    }


    public static void addOff(Seller seller, Discount newDiscount) {
        //TODO
    }

    public static void editOff(Seller seller, Discount discount) {
        //TODO
    }

    public static ArrayList<String> showCategories() {
        return Category.showCategories();
    }

    public static int viewBalance(Seller seller) {
        return seller.getCredit();
    }
    public static void checkProductByID(String id) throws Exception{
        if(Product.isThereProductWithId(id)){
        }else {
            throw new Exception("There isn't any product with this id");
        }
    }
    public static ArrayList<String> viewOffs(Seller seller){
        return seller.getDiscountInfo(seller);
    }

}
