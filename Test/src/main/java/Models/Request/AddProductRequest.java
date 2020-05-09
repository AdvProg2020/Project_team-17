package Models.Request;

import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Product;

public class AddProductRequest extends Request {
    private String productId;
    private ProductEnum productStatus;
    private String productName;
    private String companyName;
    private double price;
    private Seller productSeller;
    private Category category;
    private String productExplanation;

    public AddProductRequest(String productId, ProductEnum productStatus, String productName, String companyName,
                             double price, Category category, Seller productSeller,String productExplanation) {
        super("add product -> "+ allRequests.size());
        this.productId = productId;
        this.productStatus = productStatus;
        this.productName = productName;
        this.companyName = companyName;
        this.price = price;
        this.category=category;
        this.productSeller = productSeller;
        this.productExplanation=productExplanation;
    }

    @Override
    public void acceptRequest() {
        Product product =new Product(productId, productStatus, productName, companyName, price,
                productSeller, category,productExplanation,0);
        productSeller.addProduct(productSeller,product);
        category.addProductToThisCategory(category,product);
    }
}
