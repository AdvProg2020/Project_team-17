package Models;

import Models.Enums.RequestStatusEnum;
import Models.Enums.RequestTypeEnum;

import java.util.HashMap;

public class Request {
    private int requestId;
    static private int lastRequestCode = 0;
    private RequestTypeEnum requestType;
    private RequestStatusEnum requestStatus;
    private HashMap<String, String> information;

    public Request(String requestType, HashMap<String, String> information) {
        this.requestId = lastRequestCode + 1;
        lastRequestCode++;
        this.requestType=requestTypeFinder(requestType);
        this.information = information;
        this.requestStatus = RequestStatusEnum.PROCESSING;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getLastRequestCode() {
        return lastRequestCode;
    }

    public RequestTypeEnum getRequestType() {
        return requestType;
    }

    public RequestStatusEnum getRequestStatus() {
        return requestStatus;
    }

    public HashMap<String, String> getInformation() {
        return information;
    }

    RequestTypeEnum requestTypeFinder(String typeName) {
        switch (typeName) {
            case "register seller":
                return RequestTypeEnum.REGISTER_SELLER;
            case "add product":
                return RequestTypeEnum.ADD_PRODUCT;
            case "edit product":
                return RequestTypeEnum.EDIT_PRODUCT;
            case "remove product":
                return RequestTypeEnum.REMOVE_PRODUCT;
            case "add sale":
                return RequestTypeEnum.ADD_SALE;
            case "edit sale":
                return RequestTypeEnum.EDIT_SALE;
            case "add product to sale":
                return RequestTypeEnum.ADD_PRODUCT_TO_SALE;
            case "remove product from sale":
                return RequestTypeEnum.REMOVE_PRODUCT_FROM_SALE;
            case "add comment":
                return RequestTypeEnum.ADD_COMMENT;
        }
        return null;
    }

    public void acceptRequest() {
        this.requestStatus = RequestStatusEnum.ACCEPTED;
    }

    public void declineRequest() {
        this.requestStatus = RequestStatusEnum.DECLINED;
    }
}


