package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Discount;
import Models.Enums.DiscountEnum;
import Models.Enums.RequestStateEnum;
import Models.Enums.RequestTypeEnum;

import java.io.IOException;

public class AddOffRequest extends Request {
    private Discount discount;

    public AddOffRequest(Seller seller, Discount discount) throws IOException {
        super("Add off ---> " + (allRequests.size() + 1), seller);
        this.type = RequestTypeEnum.ADD_OFF;
        this.discount = discount;
    }

    @Override
    public void accept() {
        discount.setDiscountState(DiscountEnum.ACCEPTED);
        seller.addDiscount(discount);
        this.setState(RequestStateEnum.ACCEPTED);
    }

    @Override
    public String toString() {
        return "AddOffRequest{" +
                "discount=" + discount +
                ", id='" + id + '\'' +
                ", seller=" + seller +
                ", type=" + type +
                '}';
    }
}