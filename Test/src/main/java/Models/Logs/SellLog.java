package Models.Logs;

import Models.Accounts.Seller;
import Models.Enums.SellLogEnum;
import Models.Logs.Log;
import Models.Product;

import java.util.Date;

public class SellLog extends Log {
    private int amountReceived;
    private int reducedAmountForDiscount;
    private Product soldProduct;
    private String sellerName;
    private SellLogEnum sellLogState;

    public SellLog(String logId, Date date, int amountReceived, int reducedAmountForDiscount, Product soldProduct, String sellerName, SellLogEnum sellLogState) {
        super(logId, date);
        this.amountReceived = amountReceived;
        this.reducedAmountForDiscount = reducedAmountForDiscount;
        this.soldProduct = soldProduct;
        this.sellerName = sellerName;
        this.sellLogState = sellLogState;
    }
    public void setSellLogForSeller(String sellerName){
        Seller seller = Seller.getSellerWithName(sellerName);
        seller.setSellLog(this);
    }
}
