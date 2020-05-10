package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Enums.RequestTypeEnum;
import Models.Product;

public class AddProductRequest extends Request {
    private Product product;
    private Category category;

    public AddProductRequest(String id, Seller seller, Manager manager, Product product,Category category) {
        super(id, seller, manager);
        this.type = RequestTypeEnum.ADD_PRODUCT;
        this.product = product;
        this.category=category;
    }

    @Override
    public void accept() {
        product.setProductState(ProductEnum.ACCEPTED);
        seller.addProduct(seller,product);
        category.addProductToThisCategory(category,product);
    }

    @Override
    public String toString() {
        return "AddProductRequest{" +
                "product=" + product +
                ", id='" + id + '\'' +
                ", seller=" + seller +
                ", type=" + type +
                '}';
    }
}