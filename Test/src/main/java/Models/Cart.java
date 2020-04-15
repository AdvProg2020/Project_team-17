package Models;

import java.util.HashMap;

public class Cart {
    private HashMap<Product,Integer> productsInCart = new HashMap<>();
    private Customer customer;
    //nemidonam bayad constructor bezarim inja ya na

    public void addProductToCart(Customer customer, Product product){
        productsInCart.put(product,1);
        customer.setCart(this);  //motmaen nistam in karam doroste ya na
    }
    public void increaseNumberOfProduct(Product product){

    }
    public void decreaseNumberOfProduct(Customer customer , Product product){

    }

}
