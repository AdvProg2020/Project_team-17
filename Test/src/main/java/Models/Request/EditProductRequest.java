package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Enums.ProductEnum;
import Models.Enums.RequestTypeEnum;
import Models.Product;

public class EditProductRequest extends Request {
    private Product product;

    public EditProductRequest(Seller seller, Manager manager, Product product) {
        super("Edit product ---> "+allRequests.size()+1, seller, manager);
        this.type = RequestTypeEnum.EDIT_PRODUCT;
        this.product = product;
    }

    public void accept() {
        product.setProductState(ProductEnum.ACCEPTED);
    }

    @Override
    public String toString() {
        return "EditProductRequest{" +
                "product=" + product +
                ", id='" + id + '\'' +
                ", seller=" + seller +
                ", type=" + type +
                '}';
    }
}
