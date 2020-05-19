package Models;

import Models.Accounts.Customer;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> productsInCart = new HashMap<>();

    public void addProductToCart(Customer customer, Product product) {
        Cart cart = customer.getCart();
        if (cart.isThereProductInCart(product)) {
            int count = productsInCart.get(product);
            productsInCart.put(product, count + 1);
        } else {
            cart.productsInCart.put(product, 1);
        }
        customer.setCart(this);
    }

    public void increaseNumberOfProduct(Product product) {
        for (Product productInCart : productsInCart.keySet()) {
            if (productInCart.equals(product)) {
                int count = productsInCart.get(productInCart);
                productsInCart.put(productInCart, count + 1);
            }
        }
    }

    public void decreaseNumberOfProduct(Product product) {
        // remove ro bayad check kard doroste ya na anemidonam az hashmap che shekli remove konam
        for (Product cartProduct : productsInCart.keySet()) {
            if (cartProduct.equals(product)) {
                int count = productsInCart.get(cartProduct);
                productsInCart.put(cartProduct, count - 1);
                if (productsInCart.get(cartProduct) == 0) {
                    productsInCart.remove(cartProduct);
                }
            }
        }
    }

    public void removeProductAfterPurchasingFromCart(Product product) {
        this.productsInCart.remove(product);
    }

    public ArrayList<Product> showProductsOfCart() {
        ArrayList<Product> allProductOfCart = new ArrayList<>();
        for (Product product : productsInCart.keySet()) {
            allProductOfCart.add(product);
        }
        return allProductOfCart;
    }

    public ArrayList<Product> getProductsInCart() {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : productsInCart.keySet()) {
            products.add(product);
        }
        return products;
    }

    public boolean isThereProductInCart(Product product) {
        for (Product productInCart : productsInCart.keySet()) {
            if (productInCart.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public Product getProductInCart(String id) {
        for (Product product : this.productsInCart.keySet()) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public double totalPriceOfProductInCart() {
        double sum = 0;
        for (Product product : this.productsInCart.keySet()) {
            sum += product.getPrice();
        }
        return sum;
    }

    public double totalPriceWithDiscount(DiscountCode discountCode) {
        return totalPriceOfProductInCart() - DiscountCode.calculateDiscountAmount(totalPriceOfProductInCart(), discountCode);
    }


}
