package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Enums.RequestTypeEnum;
import Models.Product;

import java.util.ArrayList;

public class AddProductRequest extends Request {
    private Product product;
    private Category category;

    public AddProductRequest(Seller seller, Manager manager, Product product, Category category) {
        super("Add product ---> " + (allRequests.size() + 1), seller, manager);
        this.type = RequestTypeEnum.ADD_PRODUCT;
        this.product = product;
        this.category = category;

    }
    /*public static boolean isThereAddProductRequestWithThisID(String id){
        for (AddProductRequest addProductRequest : addProductRequests) {
            if(addProductRequest.getId().equals(id)){
                return true;
            }
        }return false;
    }
    public static AddProductRequest getRequest(String id){
        for (AddProductRequest addProductRequest : addProductRequests) {
            if(addProductRequest.getId().equals(id)){
                return addProductRequest;
            }
        }return null;
    }*/

    @Override
    public void accept() {
        product.setProductState(ProductEnum.ACCEPTED);
        seller.addProduct(seller, product);
        category.addProductToThisCategory(category, product);
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
