package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Enums.RequestStateEnum;
import Models.Enums.RequestTypeEnum;
import Models.Product;

import java.io.IOException;

public class AddProductRequest extends Request {
    private Product product;
    private Category category;

    public AddProductRequest(Seller seller,Product product, Category category) throws IOException {
        super("Add product ---> " + (allRequests.size() + 1), seller);
        this.type = RequestTypeEnum.ADD_PRODUCT;
        this.product = product;
        this.category = category;

    }

    @Override
    public void accept() {
        product.setProductState(ProductEnum.ACCEPTED);
        seller.addProduct(seller, product);
        category.addProductToThisCategory(category, product);
        this.setState(RequestStateEnum.ACCEPTED);
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
