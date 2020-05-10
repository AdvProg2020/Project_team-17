package Models.Request;

import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Discount;
import Models.Enums.DiscountEnum;
import Models.Enums.RequestTypeEnum;

public class EditOffRequest extends Request {
    private Discount discount;

    public EditOffRequest(String id, Seller seller, Manager manager, Discount discount) {
        super(id, seller, manager);
        this.type = RequestTypeEnum.EDIT_OFF;
        this.discount = discount;
    }

    public void accept() {
        discount.setDiscountState(DiscountEnum.ACCEPTED);
    }

    @Override
    public String toString() {
        return "EditOffRequest{" +
                "discount=" + discount +
                ", id='" + id + '\'' +
                ", seller=" + seller +
                ", type=" + type +
                '}';
    }
}