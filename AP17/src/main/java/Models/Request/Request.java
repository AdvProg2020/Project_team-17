package Models.Request;

import Models.Accounts.Seller;
import Models.Enums.RequestStateEnum;
import Models.Enums.RequestTypeEnum;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Request implements Serializable {
    protected static ArrayList<Request> allRequests = new ArrayList<Request>();
    protected String id;
    protected Seller seller;
    protected RequestTypeEnum type;
    protected RequestStateEnum state;

    public Request(String id, Seller seller) throws IOException {
        this.id = id;
        this.seller = seller;
        this.state = RequestStateEnum.PENDING_TO_ACCEPT;
        allRequests.add(this);
//        WriteIntoFile.writeRequestsIntoFile();
    }

    public abstract void accept();

    public static ArrayList<Request> showRequestsNeedToBeAccepted() {
        ArrayList<Request> pendingRequest = new ArrayList<>();
        for (Request request : allRequests) {
            if (request.getState().equals(RequestStateEnum.PENDING_TO_ACCEPT)) {
                pendingRequest.add(request);
            }
        }
        return pendingRequest;
    }


    public static Request getRequestById(String id) {
        for (Request request : allRequests) {
            if (request.getId().equals(id)) {
                return request;
            }
        }
        return null;
    }

    public static boolean isThereRequestById(String id) {
        for (Request request : allRequests) {
            if (request.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void deleteRequest(Request request) {
        allRequests.remove(request);
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getId() {
        return id;
    }

    public RequestStateEnum getState() {
        return state;
    }

    public void setState(RequestStateEnum state) {
        this.state = state;
    }

    @Override
    public abstract String toString();
}